package org.worklog.config;

/**
 * This enumeration contains configuration item information
 * 
 * @author Ahmed Bannany
 */
public enum ConfigItem {

    WEB_LOCATION("web-location", "./public");

    private String name;
    private String defaultValue;

    /**
     * Construct
     * 
     * @param name Configuration key name
     * @param defaultName The default name
     */
    private ConfigItem(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    /**
     * Get the name/key of configuration item 
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the default value
     * 
     * @return The default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }
}
