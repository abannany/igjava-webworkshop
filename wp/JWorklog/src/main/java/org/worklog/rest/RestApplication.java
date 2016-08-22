package org.worklog.rest;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.worklog.config.WorklogConf;

import spark.Spark;

/**
 * THis class is used to initialize the Log resource Rest API
 */
public class RestApplication {

    @Inject
    private LogResource logResource;

    @Inject
    private WorklogConf worklogConf;

    /**
     * Initializes the Rest Application
     */
    @PostConstruct
    public void init() {
        Spark.externalStaticFileLocation(worklogConf.getWebLocation());
        Spark.port(12345);
        CorsFilter.apply();
        logResource.init();
    }

    public WorklogConf getWorklogConf() {
        return worklogConf;
    }
}
