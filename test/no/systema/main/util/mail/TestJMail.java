package no.systema.main.util.mail;

import org.junit.Before;
import org.junit.Test;

import no.systema.main.util.constants.AppConstants;

public class TestJMail {

	Mail mail = null;
	
	
	@Before
	public void setUp() throws Exception {
		mail = new Mail();
	}

	@Test
	public final void testSendMail() {
		Mail mail = new Mail();
		StringBuilder subject = new StringBuilder("Brønnøysundregisteret og Enhetsregisteret sere ut til å ha problemer.");
		StringBuilder message = new StringBuilder("eSpedsg kan ikke få data på denne url:");
		message.append("\n\n\n\n");
		message.append("::Detta mail har skickats av eSpedsg.::");
		message.append("\n");
		message.append("::fra:"+AppConstants.MAIL_USERNAME);
		message.append(" til:"+AppConstants.MAIL_BOX_SUPPORT+"::");
		mail.sendMail(AppConstants.MAIL_BOX_SUPPORT,subject.toString(), message.toString());
	}

}
