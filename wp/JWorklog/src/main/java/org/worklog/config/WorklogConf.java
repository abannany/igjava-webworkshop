package org.worklog.config;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

/**
 * This class is used to read the configuration from a file. The configuration items can be overwritten by application arguments.
 * 
 * @author Ahmed Bannany
 */
public class WorklogConf {

    private static final String CONF_APPLICATION_PROPERTIES = "./conf/application.properties";

    @Inject
    private CompositeConfiguration config;

    @PostConstruct
    public void init() {
        config.addConfiguration(new SystemConfiguration());
        try {
            config.addConfiguration(new PropertiesConfiguration(CONF_APPLICATION_PROPERTIES));
        } catch (ConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Gets the location where the web stuff are located
     * 
     * @return The location
     */
    public String getWebLocation() {
        return config.getString(ConfigItem.WEB_LOCATION.getName(), ConfigItem.WEB_LOCATION.getDefaultValue());
    }
}
