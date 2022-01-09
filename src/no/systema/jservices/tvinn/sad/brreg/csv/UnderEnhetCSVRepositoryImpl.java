package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;
import no.systema.jservices.tvinn.sad.z.maintenance.felles.model.dao.services.FirmDaoServices;
import no.systema.main.util.ApplicationPropertiesUtil;

/**
 * This class populates a HashMap with all values in UnderEnhetsregisteret i
 * brreg.no
 * 
 * Values are downloaded in a gzip CVS-file and then inserted into Map.
 * 
 * 
 * @author Fredrik Möller
 * @date Dec, 2016
 */
public class UnderEnhetCSVRepositoryImpl implements UnderEnhetCSVRepository {
	private static Logger logger = LoggerFactory.getLogger(UnderEnhetCSVRepositoryImpl.class.getName());
	private FileHelper fileHelper = null;

	// For test
	// private String csvDownloadUrl =
	// "http://data.brreg.no/enhetsregisteret/download/underenheter";
	// private String filePath="WebContent/WEB-INF/resources/files/";
	//// private String csvFile = "underenheter-minor.csv";
	// private String csvFile = "underenheter.csv";
	// private String csvGzFile = "underenheter.csv.gz";
	private String downloadUrl = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.download.url");
	private String headerAccept = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.download.accept");
	private String filePath = ApplicationPropertiesUtil.getProperty("no.brreg.data.resources.filepath");
	private String file = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.file");
	private String gzFile = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.gz.file");

	private List<UnderEnhet> underEnheter = null;

	@Override
	public UnderEnhet get(String orgNr) throws IOException {
		if (underEnheter == null) {
			putAllUnderEnhetIntoMap();
		}

		UnderEnhet f_enhet = underEnheter.stream()
				  .filter(enhet -> orgNr.equals(enhet.getOrganisasjonsnummer()))
				  .findAny()
				  .orElse(null);		
		
		return f_enhet;


	}

	@Override
	public void downloadFile() throws IOException {
		try {
			fileHelper.downloadFile(headerAccept, downloadUrl, filePath, gzFile);
			fileHelper.unGzip(gzFile, filePath, file);
		} catch (RestClientException ex) {
			logger.info("RestClientException encountered: ", ex);
			throw ex;
		} catch (IOException ex) {
			logger.info("IOException encountered: ", ex);
			throw ex;
		}
	}

	private void loadFileIntoMap() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String pathName = FileHelper.CATALINA_BASE + filePath + file;

		try {

			File file = new File(pathName);
			logger.info("file="+file.getAbsolutePath());
			logger.info("ObjectMapper reading values from file into List...");
			underEnheter = objectMapper.readValue(file,new TypeReference<List<UnderEnhet>>() {});
			logger.info("..ready");

		} catch (IOException e) {
			logger.info("ERROR retrieving file from pathName="+pathName+". Exception=", e);
			throw e;
		}
	}	


	private void putAllUnderEnhetIntoMap() throws IOException {
		if (firmDaoServices.isNorwegianFirm()) {
			logger.info("::isNorwegianFirm::, putAllUnderEnhetIntoMap");
			fileHelper = new FileHelper(restTemplate);
			downloadFile();
			logger.info("file downloaded.");
			loadFileIntoMap();
			logger.info("file loaded into Map.");
		}
	}

	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Autowired
	@Required
	@Override
	public void setRestTemplate(RestTemplate value) {
		this.restTemplate = value;
	}

	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	@Qualifier("firmDaoServices")
	private FirmDaoServices firmDaoServices;

	@Autowired
	@Required
	public void setFirmDaoServices(FirmDaoServices value) {
		this.firmDaoServices = value;
	}

	public FirmDaoServices getFirmDaoServices() {
		return this.firmDaoServices;
	}

}
