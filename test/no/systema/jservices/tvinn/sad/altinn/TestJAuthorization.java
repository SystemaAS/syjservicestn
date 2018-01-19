package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import no.systema.jservices.tvinn.sad.altinn.proxy.Authorization;
import no.systema.jservices.tvinn.sad.altinn.proxy.AuthorizationServiceException;

public class TestJAuthorization {

	Authorization auth = null;
	
	
	@Before
	public void setUp() throws Exception {
		auth = new Authorization();
	}

	@Test
	public void test() throws AuthorizationServiceException {
		String cookie = auth.getCookie();
		System.out.println("cookie="+cookie);
		assertNotNull("Not yet implemented, but cookie is", cookie);
	}
}
