package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpEntity;

import no.systema.jservices.tvinn.sad.altinn.proxy.ApiKeyDto;
import no.systema.jservices.tvinn.sad.altinn.proxy.Authorization;

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
	public void testAnyThing() {
		HttpEntity<ApiKeyDto> ent = auth.getHttpEntity();
		logger.info("ent="+ent);
		assertNotNull("checking", ent);
	}	
	
}
