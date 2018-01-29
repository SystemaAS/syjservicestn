package no.systema.jservices.tvinn.sad.altinn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.tvinn.sad.altinn.entities.ApiKey;

public class SomeThings {
	private static Logger logger = Logger.getLogger(SomeThings.class.getName());
	String UserName = "kalleanka1";
	String UserPassword = "kalleanka2";
	//String UserPassword = "ajhhs";
	
	public String get() throws Exception {

//		char[] password = "KRw16s7XVQuyA3ed".toCharArray();
//		
//		char[] password2 = "after8".toCharArray();
//		File certFile = ResourceUtils.getFile(
//				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12");
//		File certFile2 = ResourceUtils.getFile(
//				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.jks");
//		logger.info("certFile=" + certFile);
		// SSLContext sslContext = SSLContextBuilder
		// .create()
		// .loadKeyMaterial(loadP12("classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12",
		// password), password)
		// //
		// .loadTrustMaterial(ResourceUtils.getFile("classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12"),
		// password)
		// // .loadTrustMaterial(trustStoreFile, password)
		// .build();
		// SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		// sslContext,
		// SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);
		// CloseableHttpClient httpclient = HttpClients.custom()
		// .setSSLSocketFactory(sslsf)
		// .build();

		// InputStream is = new FileInputStream("cacert.crt");
		// You could get a resource as a stream instead.

		
//        KeyStore clientStore = KeyStore.getInstance("PKCS12");
//        clientStore.load(new FileInputStream(certFile), password);
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        kmf.init(clientStore, password);
//        KeyManager[] kms = kmf.getKeyManagers();
//
//        KeyStore trustStore = KeyStore.getInstance("JKS");
//        trustStore.load(new FileInputStream(certFile2), password2);
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        tmf.init(trustStore);
//        TrustManager[] tms = tmf.getTrustManagers();
//
//        SSLContext sslContext = null;
//        sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kms, tms, new SecureRandom());

//        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//        URL url = new URL("https://tt02.altinn.no/api/authentication/authenticatewithpassword?ForceEIAuthentication");
//        HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();		
//		logger.info("urlConn="+urlConn);
		//urlConn.connect();


        // Allow TLSv1 protocol only
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                sslContext,
//                new String[] { "TLSv1" },
//                null,
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//        CloseableHttpClient httpclient = HttpClients.custom()
//                .setSSLSocketFactory(sslsf)
//                .build();        
//        

  //Prova denna : https://stackoverflow.com/questions/38375915/https-client-java-with-p12-certificate      
        
		URI uri = URI
				.create("https://tt02.altinn.no/api/authentication/authenticatewithpassword?ForceEIAuthentication");
        
        RequestBuilder requestBuilder = RequestBuilder.post();
		requestBuilder.setUri(uri);
		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, "application/hal+json");
		requestBuilder.setHeader(HttpHeaders.HOST, "tt02.altinn.no");
		requestBuilder.setHeader("ApiKey", "D26F6857-3ADF-46D4-81DD-F5C87978C807");
		String body = "{\"UserName\": \"" + UserName + "\",  \"UserPassword\": \"" + UserPassword + "\" }";
		StringEntity httpEntity = new StringEntity(body, Charset.forName("utf-8"));
		requestBuilder.setEntity(httpEntity);

		HttpUriRequest m = requestBuilder.build();
//		HttpResponse response;

		
		
//        String referenceDataUri = UriComponentsBuilder
//                .fromHttpUrl(referenceDataUrl + "/subjects")
//                .queryParam("uri", uri)
//                .toUriString();
		
		
		try {

			RestTemplate restTemplate = new RestTemplate(getRequestFactory());
			 
//			HttpEntity<String> request = new HttpEntity<>(new String("bar"));
//			String foo = restTemplate.postForObject(fooResourceUrl, request, String.class);			
//			
//			 HttpHeaders headers = new HttpHeaders();
//			 headers.setContentType(MediaType.TEXT_PLAIN);
//			 HttpEntity entity = new HttpEntity("helloWorld", headers);
//			 URI location = restTemplate.postForLocation(uri, entity);

			ApiKey apiKey = new ApiKey();		
			apiKey.setUserName(UserName);
			apiKey.setUserPassword(UserPassword);
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//			headers.add("HeaderName", "value");
//			headers.add("Content-Type", "application/json");
			headers.add(HttpHeaders.CONTENT_TYPE, "application/hal+json");
			headers.add(HttpHeaders.HOST, "tt02.altinn.no");
			headers.add("ApiKey", "D26F6857-3ADF-46D4-81DD-F5C87978C807");
			
			
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<ApiKey> entity = new HttpEntity<ApiKey>(apiKey, headers);

//			String xx = restTemplate.postForObject(uri, request2, String.class);	
//			String xx = restTemplate.exchange(uri, request2, String.class);		
			ResponseEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.POST, entity, byte[].class);			
			logger.info("response="+response);
			
			logger.info("cookie"+response.getHeaders().COOKIE);
			
			response.getHeaders().forEach((k,v)->System.out.println("key: " + k + " value : " + v));

			List<String> cookie = null;
//			response.getHeaders().forEach((key,value)->{
//				if("Set-Cookie".equals(key)){
//					System.out.println("Hello Cookie:"+value);
//					value.toString()  -> cookie;
//				}
//			});
			
			cookie = response.getHeaders().get("Set-Cookie");
			
			logger.info("cookie="+cookie);
			
//			 ResponseEntity<String> response2 = (ResponseEntity<String>) restTemplate.exchange(uri, HttpMethod.POST, request, String.class);			 
			 
			
////			 CloseableHttpClient httpclient = HttpClients.custom().build();
//			ClientHttpRequestFactory httpFactory = getRequestFactory();
//			
//			response = getHttpClient().execute(m);
//			ClientHttpRequest httpRequest = httpFactory.createRequest(uri, HttpMethod.POST);
//			response = getHttpClient().execute(m);
//			ClientHttpResponse httpResponse =  httpRequest.execute();
//			logger.debug(response);
//			HttpEntity entity = response.getEntity();
//			logger.info("httpResponse="+httpResponse);
////			logger.info("status="+httpResponse.getStatusText());
//					
//			return EntityUtils.toString(entity);
			return "xXx";
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unable to get", e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Unable to get ", e);
		}
	}

	private KeyStore loadP12(String file, char[] password) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		File key = ResourceUtils.getFile(file);
		try (InputStream in = new FileInputStream(key)) {
			keyStore.load(in, password);
		}
		return keyStore;
	}

///båda filerna https://stackoverflow.com/questions/6994944/connect-to-a-https-site-with-a-given-p12-certificate
//keytool -importkeystore -srckeystore Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12 -srcstoretype PKCS12 -deststoretype JKS -destkeystore Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.jks

//Funkar ganska bra: https://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post	
	
	
	public void get2() throws Exception {
		File certFile = ResourceUtils.getFile(
				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12");
//		File certFile2 = ResourceUtils.getFile(
//				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.jks");

		System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
//		System.setProperty("javax.net.ssl.trustStoreType", "jks");
		System.setProperty("javax.net.ssl.keyStore", certFile.getAbsolutePath());
//		System.setProperty("javax.net.ssl.trustStore", certFile2.getAbsolutePath());
		System.setProperty("javax.net.debug", "ssl");
//		System.setProperty("javax.net.ssl.trustStorePassword", "after8");
		System.setProperty("javax.net.ssl.keyStorePassword", "KRw16s7XVQuyA3ed");
        System.setProperty("https.protocols", "TLSv1.1");


		try {
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			URL url = new URL(
					"https://tt02.altinn.no/api/authentication/authenticatewithpassword?ForceEIAuthentication");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(sslsocketfactory);
			
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/hal+json");
			conn.setRequestProperty(HttpHeaders.HOST, "tt02.altinn.no");
			conn.setRequestProperty("ApiKey", "D26F6857-3ADF-46D4-81DD-F5C87978C807");
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);			
	
			logger.info("FRMO0");				
	
	        String body = "{\"UserName\": \""+ UserName+ "\",  \"UserPassword\": \""+UserPassword+"\" }";			
			conn.setFixedLengthStreamingMode(body.getBytes().length);
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			out.print(body);
			out.close();

			
			String[] cs = sslsocketfactory.getSupportedCipherSuites();
			logger.info("cs lenght="+cs.length);
			String[] defCs = sslsocketfactory.getDefaultCipherSuites();
			logger.info("defCs lenght="+defCs.length);
			for (int i = 0; i < cs.length; i++) {
				logger.info("supported cs:"+cs[i]);
			}
			
			for (int i = 0; i < defCs.length; i++) {
				logger.info("default cs:"+defCs[i]);
			}			
			
			
			
			conn.connect();			
			
//			OutputStream os = conn.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(
//			        new OutputStreamWriter(os, "UTF-8"));
////			writer.write(getQuery(params));
//			writer.flush();
//			writer.close();
//			os.close();

			logger.info("FRMO1");			
			
			
			conn.connect();
			
			logger.info("FRMO2");
			
			System.out.println(conn.getResponseCode());
			InputStream inputstream = conn.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

			String string = null;
			while ((string = bufferedreader.readLine()) != null) {
				System.out.println("Received " + string);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	   /**
     * Configures ClientHttpRequestFactory to provide client certificate for two way https connection.
     *
     * @return the ClientHttpRequestFactory with configured SSLContext
     * @throws KeyStoreException
     * @throws IOException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws KeyManagementException
     */

//    public ClientHttpRequestFactory getRequestFactory() throws KeyStoreException, IOException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, KeyManagementException {
    public HttpClient getHttpClient() throws KeyStoreException, IOException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, KeyManagementException {

    	String[] TLS_PROTOCOLS = {"TLSv1", "TLSv1.1" /*, "TLSv1.2"*/}; // Comment in TLSv1.2 to fail : bug in altinn or java that fails TLS handshake most of the time, but not always
//        String[] TLS_PROTOCOLSx = {"TLSv1.2"};
        String[] CIPHER_SUITES = null; // {"TLS_RSA_WITH_AES_128_GCM_SHA256"};
        char[] password = "KRw16s7XVQuyA3ed".toCharArray();
    	
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
		File certFile = ResourceUtils.getFile(
				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12");
		logger.debug("open ssl certificate file = "+certFile);
//        keyStore.load(new FileInputStream(new File(clientSSLCertificateKeystoreLocation)),
//                clientSSLCertificateKeystorePassword.toCharArray());

        keyStore.load(new FileInputStream(certFile),password);
        TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, password)
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        logger.info("TLS_PROTOCOLS = "+ Arrays.toString(TLS_PROTOCOLS));

        SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(
                sslContext,
                TLS_PROTOCOLS,
                CIPHER_SUITES, new DefaultHostnameVerifier());

        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(f)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

//        return requestFactory;
       return httpClient;
    }
	
  public ClientHttpRequestFactory getRequestFactory() throws KeyStoreException, IOException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, KeyManagementException {

  	String[] TLS_PROTOCOLS = {"TLSv1", "TLSv1.1" /*, "TLSv1.2"*/}; // Comment in TLSv1.2 to fail : bug in altinn or java that fails TLS handshake most of the time, but not always
   //   String[] TLS_PROTOCOLS = {"TLSv1.2"};
      String[] CIPHER_SUITES = null; // {"TLS_RSA_WITH_AES_128_GCM_SHA256"};
 //     String[] CIPHER_SUITES = {"TLS_RSA_WITH_AES_128_GCM_SHA256"};
      char[] password = "KRw16s7XVQuyA3ed".toCharArray();
  	
      KeyStore keyStore = KeyStore.getInstance("PKCS12");
		File certFile = ResourceUtils.getFile(
				"classpath:Buypass_ID-BAREKSTAD_OG_YTTERVÅG_REGNSKAP-serienummer550498454741797052332932-2015-06-24.p12");
		logger.debug("open ssl certificate file = "+certFile);
//      keyStore.load(new FileInputStream(new File(clientSSLCertificateKeystoreLocation)),
//              clientSSLCertificateKeystorePassword.toCharArray());

      keyStore.load(new FileInputStream(certFile),password);
      TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;

      SSLContext sslContext = SSLContexts.custom()
              .loadKeyMaterial(keyStore, password)
              .loadTrustMaterial(null, acceptingTrustStrategy)
              .build();

      logger.info("TLS_PROTOCOLS = "+ Arrays.toString(TLS_PROTOCOLS));

      SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(
              sslContext,
              TLS_PROTOCOLS,
              CIPHER_SUITES, new DefaultHostnameVerifier());

      HttpClient httpClient = HttpClients.custom()
              .setSSLSocketFactory(f)
              .build();

      HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
      requestFactory.setHttpClient(httpClient);

      return requestFactory;
  }

    
    public static void main(String[] args) {
		SomeThings doDaThing = new SomeThings();
		try {
			doDaThing.get();
//			doDaThing.get2();
//			ClientHttpRequestFactory factory = doDaThing.getRequestFactory();
//			URI uri = new URI(
//					"https://tt02.altinn.no/api/authentication/authenticatewithpassword?ForceEIAuthentication");
//			
//			ClientHttpRequest httpRequest = factory.createRequest(uri, HttpMethod.POST);
//			ClientHttpResponse httpResponse	= httpRequest.execute();
//			logger.info("req="+httpResponse);
//			logger.info("status="+httpResponse.getStatusText());
			
//			httpResponse.getHeaders()
			
//			getHttpClient().
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
