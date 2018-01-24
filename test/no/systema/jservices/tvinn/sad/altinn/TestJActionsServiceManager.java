package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import no.systema.jservices.tvinn.sad.altinn.proxy.ActionsServiceManager;

@PropertySource(value = { "classpath:application-test.properties" })
public class TestJActionsServiceManager {

	ActionsServiceManager serviceManager = null;
	
	
	@Before
	public void setUp() throws Exception {
        AbstractApplicationContext  context = new AnnotationConfigApplicationContext(TestAppConfig.class);
        serviceManager = (ActionsServiceManager) context.getBean("actionsservicemanager");
        context.close();			
		
	}

	@Test
	public final void testGetMessages() {
		int orgnr = 810514442;  //from Erlend exemple
		String result = serviceManager.getMessages(orgnr);
		assertNotNull(result); 
	}

	@Test
	public final void testGetProfile() {
		int orgnr = 810514442;  //from Erlend exemple
		String result = serviceManager.getProfile(orgnr);
		assertNotNull(result); 
	}	
	
}
