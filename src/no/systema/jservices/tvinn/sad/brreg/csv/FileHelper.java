package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.util.CommonClientHttpRequestInterceptor;
import no.systema.jservices.common.util.CommonResponseErrorHandler;

/**
 * This class helps managing files for HovedEnheter and Underenheter
 * 
 * 
 * @author Fredrik Möller
 * @date Dec 2, 2016,
 *
 */
public class FileHelper {
	private static Logger logger = Logger.getLogger(FileHelper.class.getName());
	private RestTemplate restTemplate = null;
	public static String CATALINA_BASE = System.getProperty("catalina.base");

	/**
	 * Constructor injects the RestTemplate
	 * 
	 * @param restTemplate
	 */
	public FileHelper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
	/**
	 * This method get file from url and save it on disk.
	 * 
	 * @param headerAccept value for Header.ACCEPT i request
	 * @param downloadUrl the url where file is hosted.
	 * @param filePath path to file.
	 * @param writeFile  the name of the file to be saved on disk. Typically a *.gz
	 * @param csvGzFile 
	 * @throws IOException 
	 */
	public void downloadFile(String headerAccept, String downloadUrl, String filePath, String writeFile) throws IOException  {
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		restTemplate.setInterceptors(Arrays.asList(new CommonClientHttpRequestInterceptor()));
		restTemplate.setErrorHandler(new CommonResponseErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, headerAccept);
		headers.add(HttpHeaders.HOST, "data.brreg.no");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		try {
			logger.info("downloadUrl="+downloadUrl);
			ResponseEntity<byte[]> response = restTemplate.exchange(downloadUrl, HttpMethod.GET, entity, byte[].class);
			logger.info("GET ready.");
			if (response != null) {
				logger.info("File downloaded from:" + downloadUrl);
			} else {
				logger.error("RESPONSE IS NULL!");
			}
			
			if (response.getStatusCode() == HttpStatus.OK) {
				ByteArrayInputStream bis = new ByteArrayInputStream(response.getBody());
				FileOutputStream fos = new FileOutputStream(CATALINA_BASE + filePath + writeFile);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = bis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				bis.close();
				
			}
		} catch (RestClientException rcex) {
			logger.info("Can not access " + downloadUrl, rcex);
			throw rcex;
		} catch (FileNotFoundException fnfex) {
			logger.info("Can not find  " + CATALINA_BASE + filePath + writeFile, fnfex);
			throw fnfex;
		} catch (IOException ioex) {
			logger.info("Can not use  " + CATALINA_BASE + filePath + writeFile, ioex);
			throw ioex;
		}
	
		logger.info("File: " + CATALINA_BASE + filePath + writeFile + " saved on disk.");
	}

	/**
	 * This method takes a gz-file and ungzip it into a decompressed file
	 * 
	 * 
	 * @param gzFile path to gx-file
	 * @param filePath path to files.
	 * @param decompressedFile path to saved ungzipped file.
	 * @throws IOException 
	 */
	public void unGzip(String gzFile, String filePath, String decompressedFile) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(CATALINA_BASE + filePath + gzFile);
			FileOutputStream fos = new FileOutputStream(CATALINA_BASE + filePath + decompressedFile);
			GZIPInputStream gzis = new GZIPInputStream(fis);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = gzis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

			fos.close();
			fis.close();
			gzis.close();

			logger.info("File:" + CATALINA_BASE + filePath + gzFile + " extracted to:" + CATALINA_BASE + filePath + decompressedFile);

		} catch (IOException ex) {
			logger.info(
					"Exception when managing gz-file " + CATALINA_BASE + filePath + gzFile + " into " + CATALINA_BASE + filePath + decompressedFile + " Exception=" , ex);
			logger.info("error:", ex);
			throw ex;
		}

	}

}
