/**
 * 
 */
package no.systema.main.util.constants;

import no.systema.main.util.ApplicationPropertiesUtil;


/**
 * All type of system constants
 * @author oscardelatorre
 *
 */
public final class AppConstants {
	//static final ResourceBundle resources = AppResources.getBundle();

	//main http root for tds CGI-calls (it varies depending on the customer installation. Please change accordingly in your application.properties file)
	public static final String HTTP_ROOT_SERVLET = ApplicationPropertiesUtil.getProperty("http.syjservices.root.servlet"); //resources.getString("http.syjservices.root.servlet");
	public static final String LOG4J_LOGGER_LEVEL = ApplicationPropertiesUtil.getProperty("log4j.logger.level"); //resources.getString("log4j.logger.level");
	
	//version
	public static final String VERSION_SYJSERVICES = ApplicationPropertiesUtil.getProperty("version.syjservices"); //resources.getString("version.syjservices");
	public static final String VERSION_SPRING = ApplicationPropertiesUtil.getProperty("version.spring"); //resources.getString("version.spring");
	
	//Mail
    /**
     * true
     */
	public static final String MAIL_SMTP_AUTH="true";
	/**
	 * 10000
	 */
    public static final int MAIL_SMTP_TIMEOUT=10000;
    /**
     * 10000
     */
    public static final int MAIL_SMTP_CONNECTIONTIMEOUT=10000;
    /**
     * true
     */
    public static final String MAIL_SMTP_STARTTLS_ENABLE="true";
    /**
     * false
     */
    public static final String MAIL_DEBUG="false";
    /**
     * "smtp.gmail.com"
     */
    public static final String MAIL_HOST="smtp.gmail.com";
    /**
     * 25
     */
    public static final int MAIL_PORT=25;
    /**
     * smtp
     */
    public static final String MAIL_PROTOCOL="smtp";
    /**
     * fredrik@systema.no
     */
    public static final String MAIL_USERNAME="fredrik@systema.no";
    /**
     * fredrik10121
     */
    public static final String MAIL_PASSWORD="fredrik10121";
    /**
     * fredrik@systema.no
     */
    public static final String MAIL_BOX_SUPPORT="fredrik@systema.no";
    /**
     * true
     */
    public static final boolean SEND_MAIL_TO_SUPPORT_BOX=true;
	   
}
