package no.systema.jservices.tvinn.sad.altinn;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import no.systema.jservices.tvinn.sad.altinn.entities.MessagesHalRepresentation;
import no.systema.jservices.tvinn.sad.altinn.entities.MetadataHalRepresentation;
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

//	Testorganisasjon: 810514442      BAREKSTAD OG YTTERVÅG REGNSKAP  (systema)
//
//	Sertifikatpassord: KRw16s7XVQuyA3ed
//
//	Daglig leder: 20015001543           ANTONIO MALIK            
//
//	 
//
//	Testorganisasjon2: 910021451 KIRKENES OG AUSTBØ (KUNDE)
//
//	Daglig leder 2: 06117701547 Rolf Bjørn	
	
	
	
	@Test
	public final void testGetMessages() {
		int orgnr = 810514442;    //810514442, 910021451
		List<MessagesHalRepresentation> result = serviceManager.getMessages(orgnr);
		
		assertNotNull(result); 
	}
	
	@Test
	public final void testGetMetadata() {
		List<MetadataHalRepresentation> result = serviceManager.getMetadata();
		
		assertNotNull(result); 
	}	
	

	@Test
	public final void testGetProfile() {
		int orgnr = 810514442;  
		try {
			serviceManager.getProfile(orgnr);
			fail("Should throw RuntimeException...401 The resource was not found");
		} catch (Exception e) {
			// expecting 401....
		}
	}	

	@Test
	public final void testGetRoles() {
		int orgnr = 810514442;  
		try {
			serviceManager.getRoles(orgnr);
			fail("Should throw RuntimeException...403 The API key is not authorized for this operation");
		} catch (Exception e) {
			// expecting 403
		}
	}	
	
	
}
