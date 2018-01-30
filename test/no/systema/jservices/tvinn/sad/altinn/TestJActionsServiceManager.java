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
import no.systema.jservices.tvinn.sad.altinn.proxy.ServiceCode;
import no.systema.jservices.tvinn.sad.altinn.proxy.ServiceEdition;
import no.systema.jservices.tvinn.sad.altinn.proxy.ServiceOwner;

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
		
		System.out.println("result.size="+result.size());
		
		assertNotNull(result); 
	}
	
	@Test
	public final void testGetMessagesForServiceOwner_Samlesider() {
		int orgnr = 810514442;    //810514442, 910021451

		List<MessagesHalRepresentation> result = serviceManager.getMessages(orgnr, ServiceOwner.Skatteetaten);
		result.forEach((message) ->  System.out.println("message from "+ServiceOwner.Skatteetaten+":"+message));
		
		assertNotNull(result); 
	}	
	
	@Test
	public final void testGetMessagesForServiceOwner_ServiceCode_ServiceEdition() {
		int orgnr = 810514442;    //810514442, 910021451

		List<MessagesHalRepresentation> result = serviceManager.getMessages(orgnr, ServiceOwner.Skatteetaten);
		result.forEach((message) ->  System.out.println("message from "+ServiceOwner.Skatteetaten+":"+message));		
		
		
		List<MessagesHalRepresentation> result2 = serviceManager.getMessages(orgnr, ServiceOwner.Skatteetaten, ServiceCode.Dagsobjor, ServiceEdition.Dagsobjor);
		result2.forEach((message) ->  System.out.println("message from "+ServiceOwner.Skatteetaten+":"+message));
		
		System.out.println("result.size="+result.size());
		System.out.println("result2.size="+result2.size());
		
		assertNotNull(result2); 
	}
	
	
	@Test
	public final void testGetMetadata() {
		List<MetadataHalRepresentation> result = serviceManager.getMetadata();
		
		assertNotNull(result); 
	}	
	
	
}
