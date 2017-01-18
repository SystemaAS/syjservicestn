package no.systema.jservices.tvinn.sad.brreg.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import no.systema.jservices.common.brreg.proxy.entities.UnderEnhet;

public class TestJUnderEnhetCSVRepository {
//	private static Logger logger = Logger.getLogger(TestJCSVRespository.class.getName());

    ApplicationContext context = null;
    UnderEnhetCSVRepository ueRepo = null;

	@Before
	public void setUp() throws Exception {
		ueRepo = new UnderEnhetCSVRepositoryImpl();	
	}

	@Test
	public final void testUeCsvRespository() throws Exception{
		ueRepo.setRestTemplate(new RestTemplate());
		UnderEnhet he = ueRepo.get(917959935);
		assertNotNull(he);

	}

}
