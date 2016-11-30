package no.systema.jservices.tvinn.sad.brreg.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import no.systema.jservices.tvinn.sad.brreg.proxy.entities.UnderEnhet;

public class TestJUnderEnhetCSVRepository {
//	private static Logger logger = Logger.getLogger(TestJCSVRespository.class.getName());

    ApplicationContext context = null;
    UnderEnhetCSVRepository ueRepo = null;

	@Before
	public void setUp() throws Exception {
/*		context = new FileSystemXmlApplicationContext("/WebContent/WEB-INF/syjservicestn-service.xml");
	    ueRepo = (HovedEnhetCSVRepository) context.getBean("hovedEnhetCSVRepository");
*/	
		ueRepo = new UnderEnhetCSVRepositoryImpl();	
	}

	@Test
	public final void testUeCsvRespository() {
		UnderEnhet he = ueRepo.get(917959935);
		assertNotNull(he);

	}

}
