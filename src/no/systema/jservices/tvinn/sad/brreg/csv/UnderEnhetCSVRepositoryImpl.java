package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.UnderEnhet;
import no.systema.main.context.JServicesServletContext;
import no.systema.main.util.ApplicationPropertiesUtil;

public class UnderEnhetCSVRepositoryImpl implements UnderEnhetCSVRepository {
	private static Logger logger = Logger.getLogger(UnderEnhetCSVRepositoryImpl.class.getName());
	private Reader in = null;
	//For test
	//private String file = "no/systema/jservices/tvinn/sad/brreg/csv/underenheter-minor.csv";
	private String file = ApplicationPropertiesUtil.getProperty("no.brreg.data.underenheter.csvfile");
	
	private Map<Integer, UnderEnhet> brregMap = null;

	@Override
	public UnderEnhet get(Integer orgNr) {
		if (brregMap == null){  //lazy load
			loadCSVFileIntoMap();
		}
		UnderEnhet ue = brregMap.get(orgNr);
		return ue;
	}


	@Override
	public void setFile(String pathAndFileName) {
		this.file = pathAndFileName;
	}
	
	private void loadCSVFileIntoMap() {
		logger.info("loadCSVFile, file=" + file);
		UnderEnhet ue = null;
		InputStream input = null;
		brregMap = new HashMap<Integer, UnderEnhet>();

		try {
			// For test
			//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//input = classLoader.getResourceAsStream(file);

			input = JServicesServletContext.getTdsServletContext().getResourceAsStream(file);
			in = new InputStreamReader(input);
			logger.info("Reader created");

			Iterable<CSVRecord> records = CSVFormat.newFormat(';').withQuote('"').withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				ue = createUnderEnhet(record);
				brregMap.put(ue.getOrganisasjonsnummer(), ue);
			}

			input.close();
			in.close();

			logger.info("Reader and InputStream closed.");

		} catch (Exception e) {
			logger.info("ERROR reading file=" + file + " Check if file exist in path! Exception=" + e);
		}
	}
	
	private UnderEnhet createUnderEnhet(CSVRecord record) {
		UnderEnhet ue = new UnderEnhet();
		String organisasjonsnummer = record.get("organisasjonsnummer");
		String registrertIMvaregisteret = record.get("registrertIMvaregisteret");
		String overordnetEnhet = record.get("overordnetEnhet");
		
		ue.setOrganisasjonsnummer(new Integer(organisasjonsnummer));
		ue.setRegistrertIMvaregisteret(registrertIMvaregisteret);
		ue.setOverordnetEnhet(new Integer(overordnetEnhet));
		
		return ue;
		
	}
	
}
