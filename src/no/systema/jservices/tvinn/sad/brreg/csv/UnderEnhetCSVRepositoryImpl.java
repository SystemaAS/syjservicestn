package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
	private Reader reader = null;
	private FileInputStream fis = null;
	private FileHelper fileHelper = null;

	// For test
	// private String csvDownloadUrl =
	// "http://data.brreg.no/enhetsregisteret/download/underenheter";
	// private String filePath="WebContent/WEB-INF/resources/files/";
	//// private String csvFile = "underenheter-minor.csv";
	// private String csvFile = "underenheter.csv";
	// private String csvGzFile = "underenheter.csv.gz";
	private String csvDownloadUrl = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.cvs.download.url");
	private String filePath = ApplicationPropertiesUtil.getProperty("no.brreg.data.resources.filepath");
	private String csvFile = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.csv.file");
	private String csvGzFile = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.csv.gz.file");

	private Map<Integer, UnderEnhet> brregMap = null;

	@Override
	public UnderEnhet get(Integer orgNr) throws IOException {
		if (brregMap == null) {
			putAllUnderEnhetIntoMap();
		}
		UnderEnhet ue = brregMap.get(orgNr);
		return ue;
	}

	@Override
	public void clearMap() {
		brregMap = null;
	}

	@Override
	public void downloadCSVFile() throws IOException {
		try {
			fileHelper.downloadFile(csvDownloadUrl, filePath, csvGzFile);
			fileHelper.unGzip(csvGzFile, filePath, csvFile);
		} catch (RestClientException ex) {
			logger.info("RestClientException encountered: ", ex);
			throw ex;
		} catch (IOException ex) {
			logger.info("IOException encountered: ", ex);
			throw ex;
		}
	}

	@Override
	public void loadCSVFileFromPath() throws IOException {
		try {
			fis = new FileInputStream(FileHelper.CATALINA_BASE + filePath + csvFile);
			reader = new InputStreamReader(fis);

		} catch (IOException e) {
			logger.info("ERROR reading file=" + FileHelper.CATALINA_BASE + filePath + csvFile + ". Check file. Exception=", e);
			throw e;
		}
	}

	private void loadCSVFileIntoMap() throws IOException {
		UnderEnhet ue = null;
		brregMap = new HashMap<Integer, UnderEnhet>();

		try {
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').withQuote('"').withFirstRecordAsHeader().parse(reader);

			for (CSVRecord record : records) {
				ue = new UnderEnhet(record);
				brregMap.put(ue.getOrganisasjonsnummer(), ue);
			}

			fis.close();
			reader.close();
			logger.info("Reader and InputStream closed.");

		} catch (IOException e) {
			logger.info("ERROR in Reader or csv parsing=" + reader + ".Exception=", e);
			throw e;
		}
	}

	private void putAllUnderEnhetIntoMap() throws IOException {
		if (firmDaoServices.isNorwegianFirm()) {
			logger.info("::isNorwegianFirm::, putAllUnderEnhetIntoMap");
			fileHelper = new FileHelper(restTemplate);
			downloadCSVFile();
			logger.info("CSV file downloaded.");
			loadCSVFileFromPath();
			loadCSVFileIntoMap();
			logger.info("CSV file loaded into Map.");
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
