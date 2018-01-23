package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.assertNotNull;

import java.util.Enumeration;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import no.systema.jservices.tvinn.sad.altinn.proxy.Authorization;
import no.systema.jservices.tvinn.sad.altinn.proxy.AuthorizationServiceException;

@PropertySource(value = { "classpath:application-test.properties" })
public class TestJAuthorization {
	private static Logger logger = Logger.getLogger(TestJAuthorization.class.getName());
	Authorization auth = null;
	ApplicationContext context = null;

	@Before
	public void setUp() throws Exception {
        AbstractApplicationContext  context = new AnnotationConfigApplicationContext(TestAppConfig.class);
        auth = (Authorization) context.getBean("authorization");
        context.close();	
	}

	@Test
	public void testAnyThing() throws AuthorizationServiceException {
		String any = auth.getAnyThing();
		logger.info("cookie="+any);
		assertNotNull("Not yet fully implemented, but any is", any);
	}	
	
//	@Test
//	public void testAuthorizedEntities() throws AuthorizationServiceException {
//		List<Entity> anyEntity = auth.getAuthorizedEntities("3");
//		logger.info("anyEntity="+anyEntity);
//		assertNotNull("Not yet fully implemented, ...", anyEntity);
//	}
	
	
	
}
