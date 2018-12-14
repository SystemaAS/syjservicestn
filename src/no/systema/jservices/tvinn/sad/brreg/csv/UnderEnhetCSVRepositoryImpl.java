package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.jservices.common.brreg.proxy.entities.HovedEnhet;
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
	private static Logger logger = Logger.getLogger(UnderEnhetCSVRepositoryImpl.class.getName());
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

	private Map<String, UnderEnhet> brregMap = null;

	@Override
	public UnderEnhet get(String orgNr) throws IOException {
		if (brregMap == null) {
			putAllUnderEnhetIntoMap();
		}

		return brregMap.get(orgNr);

	}

	@Override
	public void clearMap() {
		brregMap = null;
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
		brregMap = new HashMap<String, UnderEnhet>();
		ObjectMapper objectMapper = new ObjectMapper();
		String pathName = FileHelper.CATALINA_BASE + filePath + file;

		try {

			List<UnderEnhet> underEnheter = objectMapper.readValue(new File(pathName),new TypeReference<List<UnderEnhet>>() {});
			logger.info("Putting "+underEnheter.size() +" underEnheter into map....");
			underEnheter.forEach(underEnhet -> {
				brregMap.put(underEnhet.getOrganisasjonsnummer(), underEnhet);
			});
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
