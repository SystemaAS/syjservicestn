/**
 * 
 */
package no.systema.main.context;

import org.springframework.context.ApplicationContext;     

/**
 * This class provides application-wide access to the Spring ApplicationContext.  
 * The ApplicationContext is injected by the class "ApplicationContextProvider".
 * 
 * @author oscardelatorre
 *
 */
public class JServicesAppContext {         
    private static ApplicationContext ctx;         
    /**      
     * Injected from the class "ApplicationContextProvider" which is automatically      
     * loaded during Spring-Initialization.      
     */      
    public static void setApplicationContext(ApplicationContext applicationContext) {           
        ctx = applicationContext;       
    }         
    /**      
     * Get access to the Spring ApplicationContext from everywhere in your Application.      
     *      
     * @return      
     */      
    public static ApplicationContext getApplicationContext() {          
        return ctx; 
        
    }   
} 
