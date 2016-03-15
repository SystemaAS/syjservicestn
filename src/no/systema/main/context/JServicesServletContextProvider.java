
package no.systema.main.context;

import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.beans.BeansException;   

/**
 * This class provides an application-wide access to the  
 * ServletContext! The ServletContext is  
 * injected in a static method of the class "TdsSevletContext".  
 *  
 * 
 * @author oscardelatorre
 *
 */
public class JServicesServletContextProvider implements ServletContextAware {         
   private  ServletContext servletContext;
   
    	public void setServletContext(ServletContext context) {
    	      this.servletContext = context;
    	      // Wiring the ServletContext into a static method 
    	      JServicesServletContext.setTdsServletContext(this.servletContext);
    	}
                  
    	
       
}
  