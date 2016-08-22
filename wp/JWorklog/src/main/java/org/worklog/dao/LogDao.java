package org.worklog.dao;

import java.util.Date;
import java.util.List;

import org.worklog.model.Log;

/**
 * 
 * This interface is used for CRUD operations to persist the {@link Log}
 * 
 * @author abannany
 *
 */
public interface LogDao {

    /**
     * Find by id.
     *
     * @param id the id
     * @return the log
     */
    Log findById(long id);

    /**
     * Find by date.
     *
     * @param date the date
     * @return the log
     */
    Log findByDate(Date date);

    /**
     * Find by date between.
     *
     * @param start the start
     * @param end the end
     * @return the list
     */
    List<Log> findByDateBetween(Date start, Date end);

    /**
     * Find all.
     *
     * @return the list
     */
    List<Log> findAll();

    /**
     * Find last.
     *
     * @return the log
     */
    Log findLast();

    /**
     * Find amount.
     *
     * @return the long
     */
    long findAmount();

    /**
     * Find page.
     *
     * @param pageNumber the page number
     * @param amount the amount
     * @return the list
     */
    List<Log> findPage(int pageNumber, int amount);

    /**
     * Save.
     *
     * @param log the log
     */
    void save(Log log);

    /**
     * Update.
     *
     * @param log the log
     */
    void update(Log log);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id);
}
