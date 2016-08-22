
package org.worklog;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.worklog.rest.RestApplication;

/**
 * Startup application
 */
public class WorkLogApp {

    @Inject
    private RestApplication restApplication;
    
    /**
     * Main entry point
     * 
     * @param event CDI event
     * 
     */
    public void main(@Observes ContainerInitialized event) {
        initializeRest();
    }

    private void initializeRest() {
        restApplication.init();
    }

}
