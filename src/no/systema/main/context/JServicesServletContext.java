/**
 * 
 */
package no.systema.main.context;

import javax.servlet.ServletContext; 


/**
 * This class provides application-wide access to the ServletContext.  
 * The ServletContext is injected by the class "ServletContextProvider".
 * 
 * @author oscardelatorre
 *
 */
public class JServicesServletContext {         
    private static ServletContext ctx;         
    /**      
     * Injected from the class "TdsServletContextProvider" which is automatically      
     * loaded during Spring-Initialization.      
     */      
    public static void setTdsServletContext(ServletContext value) {           
        ctx = value;       
    }         
    /**      
     * Get access to the ServletContext from everywhere in your Application.      
     *      
     * @return      
     */      
    public static ServletContext getTdsServletContext() {          
        return ctx; 
        
    }   
} 
