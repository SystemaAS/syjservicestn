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

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.HovedEnhet;
import no.systema.main.util.ApplicationPropertiesUtil;

public class HovedEnhetCSVRepositoryImpl implements HovedEnhetCSVRepository {
	private static Logger logger = Logger.getLogger(HovedEnhetCSVRepositoryImpl.class.getName());
	private Reader reader = null;
	private FileInputStream fis = null;
	private FileHelper fileHelper = null;

	//For test
	//private String csvDownloadUrl = "http://data.brreg.no/enhetsregisteret/download/enheter";
	//private String filePath="WebContent/WEB-INF/resources/files/";
	//private String csvFile = "hovedenheter-minor.csv";
	//private String csvFile = "hovedenheter.csv";
	//private String csvGzFile = "hovedenheter.csv.gz";
	private String csvDownloadUrl = ApplicationPropertiesUtil.getProperty("no.brreg.data.hovedenheter.cvs.download.url");
	private String filePath = ApplicationPropertiesUtil.getProperty("no.brreg.data.resources.filepath");
	private String csvFile = ApplicationPropertiesUtil.getProperty("no.brreg.data.hovedenheter.csv.file");
	private String csvGzFile = ApplicationPropertiesUtil.getProperty("no.brreg.data.hovedenheter.csv.gz.file");
	private Map<Integer, HovedEnhet> brregMap = null;

	@Override
	public HovedEnhet get(Integer orgNr) {
		if (brregMap == null){  //lazy load
			fileHelper = new FileHelper(restTemplate);
			downloadCSVFile();
			loadCSVFileFromPath();
			loadCSVFileIntoMap();
		}
		HovedEnhet he = brregMap.get(orgNr);
		return he;
	}


	@Override
	public void downloadCSVFile() {
		try {
			fileHelper.downloadFile(csvDownloadUrl, filePath ,csvGzFile);
			fileHelper.unGzip(csvGzFile, filePath ,csvFile);
		} catch (RestClientException ex) {
			logger.info("RestClientException encountered: " + ex);
			ex.printStackTrace();
		} catch (IOException ex) {
			logger.info("IOException encountered: " + ex);
			ex.printStackTrace();
		}

	}
	

	@Override
	public void loadCSVFileFromPath() {
		try {
			fis = new FileInputStream(FileHelper.REAL_PATH +filePath + csvFile);
			reader = new InputStreamReader(fis);
		} catch (Exception e) {
			logger.info("ERROR reading file=" + FileHelper.REAL_PATH+ filePath + csvFile + ". Check file. Exception=" + e);
			e.printStackTrace();
		} 
	}
	
	
	private void loadCSVFileIntoMap() {
		HovedEnhet he = null;
		brregMap = new HashMap<Integer, HovedEnhet>();

		try {
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').withQuote('"').withFirstRecordAsHeader().parse(reader);

			for (CSVRecord record : records) {
				he = createHovedEnhet(record);
				brregMap.put(he.getOrganisasjonsnummer(), he);
			}

			fis.close();
			reader.close();
			logger.info("Reader and InputStream closed.");

		} catch (Exception e) {
			logger.info("ERROR in Reader or csv parsing=" + reader + ".Exception=" + e);
			e.printStackTrace();
		}
	}
	
	private HovedEnhet createHovedEnhet(CSVRecord record) {
		HovedEnhet he = new HovedEnhet();
		String organisasjonsnummer = record.get("organisasjonsnummer");
		String konkurs = record.get("konkurs");
		String registrertIMvaregisteret = record.get("registrertIMvaregisteret");
		String underAvvikling = record.get("underAvvikling");
		String underTvangsavviklingEllerTvangsopplosning = record.get("underTvangsavviklingEllerTvangsopplosning");

		he.setOrganisasjonsnummer(new Integer(organisasjonsnummer));
		he.setKonkurs(konkurs);
		he.setRegistrertIMvaregisteret(registrertIMvaregisteret);
		he.setUnderAvvikling(underAvvikling);
		he.setUnderTvangsavviklingEllerTvangsopplosning(underTvangsavviklingEllerTvangsopplosning);

		return he;
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
	
	
}
