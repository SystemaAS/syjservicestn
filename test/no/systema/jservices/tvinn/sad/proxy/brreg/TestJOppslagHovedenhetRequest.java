package no.systema.jservices.tvinn.sad.proxy.brreg;

import java.io.InputStream;
import java.net.UnknownHostException;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.tvinn.sad.brreg.proxy.Constants;
import no.systema.jservices.tvinn.sad.brreg.proxy.OppslagHovedenhetRequest;
import no.systema.jservices.tvinn.sad.brreg.proxy.entities.Hovedenhet;

public class TestJOppslagHovedenhetRequest {

	private OppslagHovedenhetRequest oppslagHovedenhetRequest;

	@Before
	public void setUp() throws Exception {
		
		oppslagHovedenhetRequest = new OppslagHovedenhetRequest(Constants.DATA_BRREG_NO_ENHETSREGISTERET_URL);
	}

	@Test
	public final void testValidOrgNr() {
		String orgNr = "974760673";
		Hovedenhet record = oppslagHovedenhetRequest.getHovedenhetRecord(orgNr);
		Assert.assertNotNull(record);
		Assert.assertEquals(record.getOrganisasjonsnummer().toString(), orgNr);
		Assert.assertThat(record.getKonkurs(), AnyOf.anyOf ( Is.is("J"), Is.is("N")));
		Assert.assertThat(record.getRegistrertIMvaregisteret(), AnyOf.anyOf ( Is.is("J"), Is.is("N")));
		Assert.assertThat(record.getUnderAvvikling(), AnyOf.anyOf ( Is.is("J"), Is.is("N")));
		Assert.assertThat(record.getUnderTvangsavviklingEllerTvangsopplosning(), AnyOf.anyOf ( Is.is("J"), Is.is("N")));
		
	}

	@Test
	public final void testInValidOrgNr() {
		String orgNr = "123456789";
		Hovedenhet record = oppslagHovedenhetRequest.getHovedenhetRecord(orgNr);
		Assert.assertNull(record);
	}
	
	@Test
	public final void testInValidOrgNrLenghtInSysped() {
		String orgNr = "1234567890";
		Hovedenhet record = oppslagHovedenhetRequest.getHovedenhetRecord(orgNr);
		Assert.assertNull(record);
	}

	
	@Test
	public final void testInValidNumberInSysped() {
		String orgNr = "fylkenr";
		Hovedenhet record = oppslagHovedenhetRequest.getHovedenhetRecord(orgNr);
		Assert.assertNull(record);
	}	
	
	@Test
	public final void testInvalidJSONStructureFromBrreg() {
		//http://data.brreg.no/enhetsregisteret/enhet/974760673.json
		oppslagHovedenhetRequest = new OppslagHovedenhetRequest("http://di.se");		
		
		try {
			Hovedenhet record = oppslagHovedenhetRequest.getHovedenhetRecord("123");
		} catch (Exception e) {
			Assert.assertTrue("Should throw UnknownHostException. e="+e.getCause(), e.getCause() instanceof UnknownHostException);
		}
				
	}
	
	private String readFile(String jsonFile) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("no/systema/jservices/tvinn/sad/proxy/brreg/"+jsonFile);  
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = input.read()) != -1) {
			builder.append((char) ch);
		}

		return builder.toString();

	}

}
