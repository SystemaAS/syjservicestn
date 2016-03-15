
package no.systema.main.context;


import org.springframework.beans.BeansException;   
import org.springframework.context.ApplicationContext;   
import org.springframework.context.ApplicationContextAware;     

/**
 * This class provides an application-wide access to the  
 * Spring ApplicationContext! The ApplicationContext is  
 * injected in a static method of the class "AppContext".  
 *  
 * Use AppContext.getApplicationContext() to get access  
 * to all Spring Beans or I18N messages
 * 
 * @author oscardelatorre
 *
 */
public class JServicesApplicationContextProvider implements ApplicationContextAware {         
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {           
        // Wiring the ApplicationContext into a static method           
        JServicesAppContext.setApplicationContext(ctx);       
    }   
}
  