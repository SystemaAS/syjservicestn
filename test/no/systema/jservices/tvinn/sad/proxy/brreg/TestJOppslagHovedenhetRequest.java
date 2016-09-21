package no.systema.jservices.tvinn.sad.proxy.brreg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.tvinn.sad.proxy.brreg.entities.Hovedenhet;

public class TestJOppslagHovedenhetRequest {

	private OppslagHovedenhetRequest oppslagHovedenhetRequest;

	@Before
	public void setUp() throws Exception {
		oppslagHovedenhetRequest = new OppslagHovedenhetRequest();
	}

	@Test
	public final void test() {
		String orgNr = "974760673";
		Hovedenhet record = oppslagHovedenhetRequest.getHovedenhetRecord(orgNr);
		Assert.assertNotNull(record);
		Assert.assertEquals(record.getOrganisasjonsnummer().toString(), orgNr);
	}

}
