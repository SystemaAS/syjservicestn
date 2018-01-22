package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import no.systema.jservices.tvinn.sad.altinn.config.AppConfig;
import no.systema.jservices.tvinn.sad.altinn.proxy.Authorization;
import no.systema.jservices.tvinn.sad.altinn.proxy.AuthorizationServiceException;

public class TestJAuthorization {

	Authorization auth = null;
	ApplicationContext context = null;
	
	@Before
	public void setUp() throws Exception {

        AbstractApplicationContext  context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        auth = (Authorization) context.getBean("authorization");
    	String cookie = auth.getCookie();        
        context.close();	
	
	}

	@Test
	public void test() throws AuthorizationServiceException {
		String cookie = auth.getCookie();
		System.out.println("cookie="+cookie);
		assertNotNull("Not yet implemented, but cookie is", cookie);
	}
}
