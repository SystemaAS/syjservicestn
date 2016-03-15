/**
 * 
 */
package no.systema.main.util.constants;

import java.util.ResourceBundle;

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
	
	
	
	
	   
}
