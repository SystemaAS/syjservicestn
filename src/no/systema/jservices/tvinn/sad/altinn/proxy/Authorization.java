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

@Service("authorization")
public class Authorization {

	private static Logger logger = Logger.getLogger(Authorization.class.getName());
	private static String[] TLS_PROTOCOLS = {"TLSv1", "TLSv1.1" /*, "TLSv1.2"*/}; // Comment in TLSv1.2 to fail : bug in altinn or java that fails TLS handshake most of the time, but not always
	private static String[] CIPHER_SUITES = null; // {"TLS_RSA_WITH_AES_128_GCM_SHA256"};

	private String CATALINA_BASE = System.getProperty("catalina.base");
	
    @Value("${what}")
    String what;	
	
    @Value("${altinn.authenticationUrl}")
    String authenticationUrl;

	@Value("${altinn.apikey}")
    private String apikey;
 
	@Value("${altinn.apiUsername}")
    private String apiUsername;	

	@Value("${altinn.apiUserpassword}")
    private String apiUserpassword;		
	
    @Value("${altinn.host}")
    private String host;    
    
    @Value("${altinn.clientSSLCertificateKeystoreLocation}")
    private String clientSSLCertificateKeystoreLocation;

    @Value("${altinn.clientSSLCertificateKeystorePassword}")
    private String clientSSLCertificateKeystorePassword;

    @Value("${altinn.serviceCode}")
    String serviceCode;

    @Value("${altinn.serviceEdition}")
    String serviceEdition;

    private ClientHttpRequestFactory requestFactory;
    
    final static String servicePath = "api/serviceowner/reportees?ForceEIAuthentication&subject=%s&servicecode=%s&serviceedition=%s";
    
    private URI authUri = null;
	
    
    @PostConstruct
	public void constructor() {
    	
    	if(CATALINA_BASE == null){
    		CATALINA_BASE = "";  //TODO for test
    		
    	} 
    	logger.info("classpath="+System.getProperty("java.class.path"));
    	
		assert authenticationUrl != null;
		logger.info("Altinn service url: " + authenticationUrl);
//
//		assert altinnServiceCode != null;
//		logger.info("Altinn service code: " + altinnServiceCode);
//
//		assert altinnServiceEdition != null;
//		logger.info("Altinn service edition: " + altinnServiceEdition);
//
		assert what != null;
		logger.info("what: " + what);		
		assert apikey != null;
		logger.info("Altinn apikey: " + apikey);

		assert apiUsername != null;
		logger.info("Altinn api username: " + apiUsername);

		assert apiUserpassword != null;
		
		assert host != null;
		logger.info("Altinn host: " + host);			
		
		assert clientSSLCertificateKeystoreLocation != null;
		logger.info("Altinn client certificate keystore location: " + clientSSLCertificateKeystoreLocation);

		assert clientSSLCertificateKeystorePassword != null;

		authUri = URI.create(authenticationUrl);
		
		
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
		char[] password = clientSSLCertificateKeystorePassword.toCharArray();

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		  ClassPathResource keyStoreLocation = new ClassPathResource(CATALINA_BASE + clientSSLCertificateKeystoreLocation);
		  String keyStoreLocation = new String(CATALINA_BASE + clientSSLCertificateKeystoreLocation);
				   
		File certFile = ResourceUtils.getFile(keyStoreLocation);
		   
		keyStore.load(new FileInputStream(certFile), password);
		TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

		SSLContext sslContext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, password)
				.loadTrustMaterial(null, acceptingTrustStrategy)
				.build();


		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, TLS_PROTOCOLS, CIPHER_SUITES,
				new DefaultHostnameVerifier());

		HttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslSocketFactory)
				.build();

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



    public String getAnyThing()  {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
		ApiKeyDto apiKeyDto = new ApiKeyDto();		
		apiKeyDto.setUserName(apiUsername);
		apiKeyDto.setUserPassword(apiUserpassword);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/hal+json");
		headers.add(HttpHeaders.HOST, host);
		headers.add("ApiKey", apikey);

		HttpEntity<ApiKeyDto> entity = new HttpEntity<ApiKeyDto>(apiKeyDto, headers);

		ResponseEntity<byte[]> response = restTemplate.exchange(authUri, HttpMethod.POST, entity, byte[].class);			
		logger.info("response="+response);
		
		List<String> cookie = response.getHeaders().get("Set-Cookie");
		String cokkie = cookie.get(0);
		logger.info("cokkie="+cokkie);
		headers.add("Cookie", cokkie);
		HttpEntity<ApiKeyDto> entityHeadersOnly = new HttpEntity<ApiKeyDto>( headers);		
//		String url = "https://tt02.altinn.no/api/910021451/messages/";  //ca: kunde
		String url = "https://tt02.altinn.no/api/810514442/messages/";  //c:a Systema 
		
		
		ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.GET, entityHeadersOnly, String.class);		//TODO handle type response	

		logger.info("response2="+response2);
		
		String responseBody = response2.getBody();
//		logger.info("responseBody="+responseBody);
		
		return responseBody;

    }

    
    String getReporteesUrl(String ssn) {
        return authenticationUrl + String.format(servicePath, ssn, serviceCode, serviceEdition);
    }
	
	
	
	
}
