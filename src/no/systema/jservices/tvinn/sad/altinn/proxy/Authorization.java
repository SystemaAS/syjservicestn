package no.systema.jservices.tvinn.sad.altinn.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class Authorization {

	private static Logger logger = Logger.getLogger(Authorization.class.getName());
	private static String[] TLS_PROTOCOLS = {"TLSv1", "TLSv1.1" /*, "TLSv1.2"*/}; // Comment in TLSv1.2 to fail : bug in altinn or java that fails TLS handshake most of the time, but not always
	private static String[] CIPHER_SUITES = null; // {"TLS_RSA_WITH_AES_128_GCM_SHA256"};

    @Value("${application.apikey}")
    private String apikey;

    @Value("${application.clientSSLCertificateKeystoreLocation}")
    private String clientSSLCertificateKeystoreLocation;

    @Value("${application.clientSSLCertificateKeystorePassword}")
    private String clientSSLCertificateKeystorePassword;

    @Value("${application.altinnServiceUrl}")
    String altinnServiceUrl;

    @Value("${application.altinnServiceCode}")
    String altinnServiceCode;

    @Value("${application.altinnServiceEdition}")
    String altinnServiceEdition;

    private ClientHttpRequestFactory requestFactory;
    
    final static String servicePath = "api/serviceowner/reportees?ForceEIAuthentication&subject=%s&servicecode=%s&serviceedition=%s";
    
    private URI uri = null;
	
	@PostConstruct
	public void constructor() {
		assert altinnServiceUrl != null;
		logger.info("Altinn service url: " + altinnServiceUrl);

		assert altinnServiceCode != null;
		logger.info("Altinn service code: " + altinnServiceCode);

		assert altinnServiceEdition != null;
		logger.info("Altinn service edition: " + altinnServiceEdition);

		assert apikey != null;
		logger.info("Altinn apikey: " + apikey);

		assert clientSSLCertificateKeystoreLocation != null;
		logger.info("Altinn client certificate keystore location: " + clientSSLCertificateKeystoreLocation);

		assert clientSSLCertificateKeystorePassword != null;
		logger.info("Altinn client certificate keystore password: " + clientSSLCertificateKeystorePassword);

		try {
			requestFactory = getRequestFactory();
		} catch (KeyStoreException | IOException | UnrecoverableKeyException | NoSuchAlgorithmException
				| CertificateException | KeyManagementException e) {
			throw new RuntimeException(e);
		}
	}	

	
	public Authorization() {
//		assert altinnServiceUrl != null;
//		logger.info("Altinn service url: " + altinnServiceUrl);
//
//		assert altinnServiceCode != null;
//		logger.info("Altinn service code: " + altinnServiceCode);
//
//		assert altinnServiceEdition != null;
//		logger.info("Altinn service edition: " + altinnServiceEdition);
//
//		assert apikey != null;
//		logger.info("Altinn apikey: " + apikey);
//
//		assert clientSSLCertificateKeystoreLocation != null;
//		logger.info("Altinn client certificate keystore location: " + clientSSLCertificateKeystoreLocation);
//
//		assert clientSSLCertificateKeystorePassword != null;
//		logger.info("Altinn client certificate keystore password: " + clientSSLCertificateKeystorePassword);

		uri = URI.create("https://tt02.altinn.no/api/authentication/authenticatewithpassword?ForceEIAuthentication"); //TODO
		
		
		try {
			requestFactory = getRequestFactory();
		} catch (KeyStoreException | IOException | UnrecoverableKeyException | NoSuchAlgorithmException
				| CertificateException | KeyManagementException e) {
			throw new RuntimeException(e);
		}
	}		
	
	
	/**
	 * Configures ClientHttpRequestFactory to provide client certificate for two
	 * way https connection.
	 *
	 * @return the ClientHttpRequestFactory with configured SSLContext
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyManagementException
	 */
	public ClientHttpRequestFactory getRequestFactory() throws KeyStoreException, IOException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, KeyManagementException {
		char[] password = "KRw16s7XVQuyA3ed".toCharArray();

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		File certFile = ResourceUtils.getFile(
				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12");
		logger.debug("open ssl certificate file = " + certFile);
		// keyStore.load(new FileInputStream(new
		// File(clientSSLCertificateKeystoreLocation)),
		// clientSSLCertificateKeystorePassword.toCharArray());

		keyStore.load(new FileInputStream(certFile), password);
		TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

		SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password)
				.loadTrustMaterial(null, acceptingTrustStrategy).build();

		logger.info("TLS_PROTOCOLS = " + Arrays.toString(TLS_PROTOCOLS));

		SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext, TLS_PROTOCOLS, CIPHER_SUITES,
				new DefaultHostnameVerifier());

		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(f).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		return requestFactory;

	}

    /**
     * Calls the authorization service.
     *
     * @param ssn the user identifier
     * @return The response from the authorization service. A set of entities where each entity represents an organization or a person.
     * @throws AuthorizationServiceException
     */
    public List<Entity> getAuthorizedEntities(String ssn) throws AuthorizationServiceException {

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.set("ApiKey", apikey);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            logger.info("Authorization request for "+ ssn);

            ResponseEntity<List<Entity>> response = restTemplate.exchange(getReporteesUrl(ssn),
                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<Entity>>() {});

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }

            throw new AuthorizationServiceException(response.getStatusCode());

        } catch (HttpClientErrorException e) {
            String message = String.format("Authorization request failed: %s", e.getLocalizedMessage());
            logger.warn(message, e);
            throw new AuthorizationServiceException(message);
        }
    }


    /**
     * Gets the callback cookie.
     *
     * @return The content in Set-Cookie when valid authenication.
     */
    public String getCookie()  {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
		ApiKeyDto apiKey = new ApiKeyDto();		
		String UserName = "kalleanka1"; //TODO
		String UserPassword = "kalleanka2"; //TODO
		apiKey.setUserName(UserName);
		apiKey.setUserPassword(UserPassword);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/hal+json");
		headers.add(HttpHeaders.HOST, "tt02.altinn.no"); //TODO
		headers.add("ApiKey", "D26F6857-3ADF-46D4-81DD-F5C87978C807");  //TODO

		HttpEntity<ApiKeyDto> entity = new HttpEntity<ApiKeyDto>(apiKey, headers);

		ResponseEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.POST, entity, byte[].class);			
		logger.info("response="+response);
		
		response.getHeaders().forEach((k,v)->System.out.println("key: " + k + " value : " + v));

		List<String> cookie = null;

		
		cookie = response.getHeaders().get("Set-Cookie");
		
		logger.info("cookie="+cookie);
		
		return cookie.toString(); //TODO

    }
   
    
    
    
    String getReporteesUrl(String ssn) {
        return altinnServiceUrl + String.format(servicePath, ssn, altinnServiceCode, altinnServiceEdition);
    }
	
	
	
	
}
