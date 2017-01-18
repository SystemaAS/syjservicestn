package no.systema.jservices.tvinn.sad.brreg.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.HovedEnhet;

public class TestJHovedEnhetCSVRepository {
//	private static Logger logger = Logger.getLogger(TestJHovedEnhetCSVRepository.class.getName());

    ApplicationContext context = null;
    HovedEnhetCSVRepository heRepo = null;

	@Before
	public void setUp() throws Exception {
/*		context = new FileSystemXmlApplicationContext("/WebContent/WEB-INF/syjservicestn-service.xml");
	    ueRepo = (HovedEnhetCSVRepository) context.getBean("hovedEnhetCSVRepository");
*/	
		heRepo = new HovedEnhetCSVRepositoryImpl();	
	}

	@Test
	public final void testHeCsvRespository() throws Exception{
		heRepo.setRestTemplate(new RestTemplate());
		HovedEnhet he = heRepo.get(917957584);
		assertNotNull(he);

	}

}
