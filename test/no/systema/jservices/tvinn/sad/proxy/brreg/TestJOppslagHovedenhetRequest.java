package no.systema.jservices.tvinn.sad.proxy.brreg;

import java.io.InputStream;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestClientException;

import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.csv.HovedEnhetCSVRepositoryImpl;
import no.systema.jservices.tvinn.sad.brreg.csv.UnderEnhetCSVRepository;
import no.systema.jservices.tvinn.sad.brreg.csv.UnderEnhetCSVRepositoryImpl;
import no.systema.jservices.tvinn.sad.brreg.proxy.OppslagEnhetRequest;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Enhet;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.HovedEnhet;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.UnderEnhet;

public class TestJOppslagHovedenhetRequest {

	private OppslagEnhetRequest oppslagHovedenhetRequest;
	private HovedEnhetCSVRepository heRepo = null; 
	private UnderEnhetCSVRepository ueRepo = null; 
	boolean CSV = false;
	boolean API = true;
	

	@Before
	public void setUp() throws Exception {
		heRepo = new HovedEnhetCSVRepositoryImpl();
		ueRepo = new UnderEnhetCSVRepositoryImpl();

		oppslagHovedenhetRequest = new OppslagEnhetRequest("http://data.brreg.no/enhetsregisteret/enhet/", heRepo, ueRepo);
	}

	@Test
	public final void testValidOrgNr() {
		Integer orgNr = 974760673;
		Enhet record = oppslagHovedenhetRequest.getEnhetRecord(orgNr, API);
		Assert.assertNotNull(record);
		Assert.assertEquals(record.getOrganisasjonsnummer(), orgNr);
		Assert.assertThat(record.getKonkurs(), AnyOf.anyOf(Is.is("J"), Is.is("N")));
		Assert.assertThat(record.getRegistrertIMvaregisteret(), AnyOf.anyOf(Is.is("J"), Is.is("N")));
		Assert.assertThat(record.getUnderAvvikling(), AnyOf.anyOf(Is.is("J"), Is.is("N")));
		Assert.assertThat(record.getUnderTvangsavviklingEllerTvangsopplosning(), AnyOf.anyOf(Is.is("J"), Is.is("N")));

	}

	@Test
	public final void testInValidOrgNr() {
		Enhet record;
		try {
			record = oppslagHovedenhetRequest.getEnhetRecord(123456789, API);
			Assert.assertNull(record);
		} catch (RestClientException e) {
			Assert.fail("Exception not supposed...");

		}
	}

	@Test
	public final void testInValidOrgNrLenghtInSysped() {
		Enhet record = oppslagHovedenhetRequest.getEnhetRecord(1234567890, API);
		Assert.assertNull(record);
	}

	@Test
	public final void testNoAccessToBrreg() {
		oppslagHovedenhetRequest = new OppslagEnhetRequest("http://kalleanka.se/", heRepo, ueRepo);

		try {
			Enhet record = oppslagHovedenhetRequest.getEnhetRecord(123, API);
			Assert.assertNull("record should be null, meaning not found",record);
		} catch (RestClientException e) {
			Assert.fail("RestClientException should NOT have been thrown");
		}

	}
	

	@Test
	public final void testNotFoundInBrregAPI() {
		try {
			Enhet record = oppslagHovedenhetRequest.getEnhetRecord(123, API);
			Assert.assertNull("record should be null, meaning not found",record);
		} catch (RestClientException e) {
			Assert.fail("RestClientException should NOT have been thrown");
		}
	}	
	
	@Test
	public final void testExistInAsHovedEnhetInCSV() {
		try {
			Enhet record = oppslagHovedenhetRequest.getEnhetRecord(917861579, CSV); //917861579 in hovedenheter-minor.csv
			Assert.assertNotNull("917861579 should have been found in csv.", record);
			HovedEnhet he = (HovedEnhet)record;
			Assert.assertNotNull("Cast to HovedEnhet should be possible.", he);
		} catch (RestClientException e) {
			Assert.fail("RestClientException should NOT have been thrown");
		}

	}
	
	
	@Test
	public final void testExistInAsUnderEnhetInCSV() {
		try {
			Enhet record = oppslagHovedenhetRequest.getEnhetRecord(917959935, CSV); //917959935 in underenheter-minor.csv
			Assert.assertNotNull("917959935 should have been found in csv.", record);
			UnderEnhet ue = (UnderEnhet)record;
			Assert.assertNotNull("Cast to UnderEnhet should be possible.", ue);
		} catch (RestClientException e) {
			Assert.fail("RestClientException should NOT have been thrown");
		}

	}	
		
	private String readFile(String jsonFile) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("no/systema/jservices/tvinn/sad/proxy/brreg/" + jsonFile);
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = input.read()) != -1) {
			builder.append((char) ch);
		}

		return builder.toString();

	}

}
