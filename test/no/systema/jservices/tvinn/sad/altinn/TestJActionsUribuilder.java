package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.tvinn.sad.altinn.proxy.ActionsUriBuilder;
import no.systema.jservices.tvinn.sad.altinn.proxy.ServiceCode;
import no.systema.jservices.tvinn.sad.altinn.proxy.ServiceEdition;
import no.systema.jservices.tvinn.sad.altinn.proxy.ServiceOwner;

public class TestJActionsUribuilder {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testProfile() {
		String host = "host";
		int orgnr = 12345678;
		URI uri = ActionsUriBuilder.profile(host, orgnr);
		assertEquals(uri.getHost(), host);
		assertEquals(uri.getPath(), "/api/12345678/profile");
	}


	@Test
	public final void testMessagesFromSamlesider() {
		String host = "host";
		int orgnr = 12345678;
		ActionsUriBuilder.messages(host, orgnr);
		URI uri = ActionsUriBuilder.messages(host, orgnr, ServiceOwner.Samlesider);
		assertEquals(uri.getHost(), host);
		assertEquals(uri.toString(), "https://host/api/12345678/messages?$filter=ServiceOwner%20eq%20'Samlesider'");
	}	

	@Test
	public final void testMessagesWithServiceOwner_ServiceCode_ServiceEdition() {
		String host = "host";
		int orgnr = 12345678;
		ActionsUriBuilder.messages(host, orgnr);
		URI uri = ActionsUriBuilder.messages(host, orgnr, ServiceOwner.Skatteetaten, ServiceCode.Dagsobjor, ServiceEdition.Dagsobjor);

		assertEquals(uri.getHost(), host);
		
		assertEquals(uri.toString(), "https://host/api/12345678/messages?$filter=ServiceOwner%20eq%20'Skatteetaten'%20and%20ServiceCode%20eq%20'5012'%20and%20ServiceEdition%20eq%20'171208'");
		
	}	
	
	
}
