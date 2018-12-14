package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

public class TestJFileHelper {

	@Test
	public final void testDownloadFile() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		FileHelper fileHelper = new FileHelper(restTemplate);

		//private String csvDownloadUrl = "http://data.brreg.no/enhetsregisteret/download/enheter";
		//private String filePath="WebContent/WEB-INF/resources/files/";
		//private String csvFile = "hovedenheter-minor.csv";		
		//private String csvGzFile = "hovedenheter.csv.gz";	
		
		fileHelper.downloadFile("application/vnd.brreg.enhetsregisteret.underenhet.v1+gzip;charset=UTF-8", "https://data.brreg.no/enhetsregisteret/api/enheter/lastned", "WebContent/WEB-INF/resources/files/", "hovedenheter.csv.gz"+LocalDate.now());
		
	}
	
	
	@Test 
	public final void testRestExchange() {
		RestTemplate restTemplate = new RestTemplate();
		
//		ResponseEntity<byte[]> response = restTemplate.exchange("http://data.brreg.no/enhetsregisteret/download/enheter", HttpMethod.GET, entity, byte[].class, "1");
	
		// Optional Accept header
		RequestCallback requestCallback = request -> request.getHeaders()
		        .setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

		// Streams the response instead of loading it all in memory
		ResponseExtractor<Void> responseExtractor = response -> {
		    // Here I write the response to a file but do what you like
		    Path path = Paths.get("WebContent/WEB-INF/resources/files/hovedenheterkalle.csv.gz");
		    System.out.println("response="+response);
		    System.out.println("response.getStatusText()="+response.getStatusText());
		    System.out.println("response.getBody()="+response.getBody());
		    Files.copy(response.getBody(), path);
	
		    
		    
		    
		    return null;
		};
		restTemplate.execute(URI.create("https://data.brreg.no/enhetsregisteret/api/enheter/lastned"), HttpMethod.GET, requestCallback, responseExtractor);		
		
		
		
	}
	
	
}
