package no.systema.jservices.tvinn.sad.brreg.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;
import no.systema.main.context.JServicesServletContext;
import no.systema.main.util.ApplicationPropertiesUtil;

public class HovedEnhetCSVRepositoryImpl implements HovedEnhetCSVRepository {
	private static Logger logger = Logger.getLogger(HovedEnhetCSVRepositoryImpl.class.getName());
	private Reader in = null;
	//For test
	//private String file = "no/systema/jservices/tvinn/sad/brreg/csv/hovedenheter-minor.csv";
	private String file = ApplicationPropertiesUtil.getProperty("no.brreg.data.hovedenheter.csvfile");
	private Map<Integer, Hovedenhet> brregMap = null;

	@Override
	public Hovedenhet get(Integer orgNr) {
		if (brregMap == null){  //lazy load
			loadCSVFileIntoMap();
		}
		Hovedenhet he = brregMap.get(orgNr);
		return he;
	}


	@Override
	public void setFile(String pathAndFileName) {
		this.file = pathAndFileName;
	}
	
	private void loadCSVFileIntoMap() {
		logger.info("loadCSVFile, file=" + file);
		Hovedenhet he = null;
		InputStream input = null;
		brregMap = new HashMap<Integer, Hovedenhet>();

		try {
			// For test
			//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			//input = classLoader.getResourceAsStream(file);

			input = JServicesServletContext.getTdsServletContext().getResourceAsStream(file);
			in = new InputStreamReader(input);
			logger.info("Reader created");

			Iterable<CSVRecord> records = CSVFormat.newFormat(';').withQuote('"').withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				he = createHovedEnhet(record);
				brregMap.put(he.getOrganisasjonsnummer(), he);
			}

			input.close();
			in.close();
			
			logger.info("Reader and InputStream closed.");

		} catch (FileNotFoundException e) {
			logger.info("ERROR reading file=" + file + "Exception=" + e);
		} catch (IOException e) {
			logger.info("ERROR reading file=" + file + "Exception=" + e);
		}
	}
	
	private Hovedenhet createHovedEnhet(CSVRecord record) {
		Hovedenhet he = new Hovedenhet();
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
	
}
