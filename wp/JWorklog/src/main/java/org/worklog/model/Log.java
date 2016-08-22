package org.worklog.model;

import java.util.Date;

/**
 * 
 * This class represents a log that a user can enter
 * 
 * @author abannany
 *
 */
public class Log {

    private long id;
    private Date date;
    private String logText;

    /**
     * Gets the log text.
     *
     * @return the log text
     */
    public String getLogText() {
        return logText;
    }

    /**
     * Sets the log text.
     *
     * @param logText
     *            the new log text
     */
    public void setLogText(String logText) {
        this.logText = logText;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date
     *            the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(long id) {
        this.id = id;
    }

}
