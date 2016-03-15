/**
 * 
 */
package no.systema.main.util;

import java.util.*;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.BeansException;


/**
 * Utility class for accessing properties from application.properties
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 *
 */
public class ApplicationPropertiesUtil extends PropertyPlaceholderConfigurer {
	private static Map<String, String> propertiesMap;
    // Default as in PropertyPlaceholderConfigurer
    private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

    @Override
    public void setSystemPropertiesMode(int systemPropertiesMode) {
        super.setSystemPropertiesMode(systemPropertiesMode);
        springSystemPropertiesMode = systemPropertiesMode;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);

        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
            propertiesMap.put(keyStr, valueStr);
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }
}
