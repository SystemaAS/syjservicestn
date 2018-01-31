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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.altinn.entities.ApiKey;

@Service("authorization")
public class Authorization {
	private static Logger logger = Logger.getLogger(Authorization.class.getName());
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

    private ClientHttpRequestFactory requestFactory;
    
    private URI authUri = null;
	
    
    @PostConstruct
	public void constructor() {
    	if(CATALINA_BASE == null){
    		CATALINA_BASE = "";  //TODO for test
    		
    	} 

    	logger.info("classpath="+System.getProperty("java.class.path"));
    	
		assert authenticationUrl != null;
		logger.info("Altinn service url: " + authenticationUrl);

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

		authUri = ActionsUriBuilder.authentication(host, authenticationUrl);
		
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
		String[] TLS_PROTOCOLS = {"TLSv1", "TLSv1.1" /*, "TLSv1.2"*/}; // Comment in TLSv1.2 to fail : bug in altinn or java that fails TLS handshake most of the time, but not always
		String[] CIPHER_SUITES = null; // {"TLS_RSA_WITH_AES_128_GCM_SHA256"};

		char[] password = clientSSLCertificateKeystorePassword.toCharArray();

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		  ClassPathResource keyStoreLocation = new ClassPathResource(CATALINA_BASE + clientSSLCertificateKeystoreLocation);
		  String keyStoreLocation = new String(CATALINA_BASE + clientSSLCertificateKeystoreLocation);
				   
		File certFile = ResourceUtils.getFile(keyStoreLocation);
		keyStore.load(new FileInputStream(certFile), password);

		/*
	     * Determines whether the certificate chain can be trusted without consulting the trust manager
	     * configured in the actual SSL context. This method can be used to override the standard JSSE
	     * certificate verification process.
	     * <p>
	     * Please note that, if this method returns {@code false}, the trust manager configured
	     * in the actual SSL context can still clear the certificate as trusted.
	     *
	     * @param chain the peer certificate chain
	     * @param authType the authentication type based on the client certificate
	     * @return {@code true} if the certificate can be trusted without verification by
	     *   the trust manager, {@code false} otherwise.
	     * @throws CertificateException thrown if the certificate is not trusted or invalid.
	     */	
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

    public HttpEntity<ApiKey> getHttpEntity()  {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
		ApiKey apiKeyDto = new ApiKey();		
		apiKeyDto.setUserName(apiUsername);
		apiKeyDto.setUserPassword(apiUserpassword);

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/hal+json");
		headers.add(HttpHeaders.ACCEPT, "application/hal+json");
		headers.add(HttpHeaders.HOST, host);
		headers.add("ApiKey", apikey);

		HttpEntity<ApiKey> entity = new HttpEntity<ApiKey>(apiKeyDto, headers);

		ResponseEntity<byte[]> response = restTemplate.exchange(authUri, HttpMethod.POST, entity, byte[].class);			
		logger.info("response="+response);
		
		List<String> setCookieList = response.getHeaders().get(HttpHeaders.SET_COOKIE);
		String cookie = null;
		try {
			cookie = setCookieList.get(0); 
		} catch (Exception e) {
			logger.error("Could not get Cookie from "+host, e);
			throw new RuntimeException("Could not get Cookie from "+host, e);
		}
		headers.add(HttpHeaders.COOKIE, cookie);
		HttpEntity<ApiKey> entityHeadersOnly = new HttpEntity<ApiKey>( headers);		
		
		return entityHeadersOnly;

    }   
    
    /**
     * Return Altinn host
     * 
     * @return host
     */
    public String getHost() {
    	return host;
    }
    
}
