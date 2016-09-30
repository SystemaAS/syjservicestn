package no.systema.main.util.mail;

import java.util.Properties;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import no.systema.main.util.constants.AppConstants;

/**
 * GMail interface 
 * 
 * @author Fredrik Möller
 * @date Sep 29, 2016
 */
public class Mail {
	private MailSender mailSender = null;

	/**
	 * Sendmail from {@link AppConstants.MAIL_USERNAME}
	 * 
	 * @param to
	 * @param subject
	 * @param msg
	 */
	public void sendMail(String to, String subject, String msg) {
		mailSender = mailSender();
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}

	
	private JavaMailSender mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", AppConstants.MAIL_SMTP_AUTH);
		properties.put("mail.smtp.timeout", AppConstants.MAIL_SMTP_TIMEOUT);
		properties.put("mail.smtp.connectiontimeout", AppConstants.MAIL_SMTP_CONNECTIONTIMEOUT);
		properties.put("mail.smtp.starttls.enable", AppConstants.MAIL_SMTP_STARTTLS_ENABLE);
		properties.put("mail.debug", AppConstants.MAIL_DEBUG);

		sender.setJavaMailProperties(properties);
		sender.setHost(AppConstants.MAIL_HOST);
		sender.setPort(AppConstants.MAIL_PORT);
		sender.setProtocol(AppConstants.MAIL_PROTOCOL);
		sender.setUsername(AppConstants.MAIL_USERNAME);
		sender.setPassword(AppConstants.MAIL_PASSWORD);

		return sender;
	}	
	
	
}
