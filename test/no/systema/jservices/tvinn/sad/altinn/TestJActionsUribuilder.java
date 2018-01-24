package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.tvinn.sad.altinn.proxy.ActionsUriBuilder;

public class TestJActionsUribuilder {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testProfile() {
		String host = "host";
		int orgnr = 12345678;
		ActionsUriBuilder.profile(host, orgnr);
		URI uri = ActionsUriBuilder.profile(host, orgnr);
		assertEquals(uri.getHost(), host);
		assertEquals(uri.getPath(), "/api/12345678/profile");
	}

}
