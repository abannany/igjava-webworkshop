package org.worklog.rest;

import java.io.StringReader;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.worklog.dao.LogDao;
import org.worklog.model.Log;

import com.google.gson.Gson;

import spark.Spark;

/**
 * This class is used add an Log resource Rest API 
 * 
 * @author abannany
 *
 */
public class LogResource {
 
    @Inject
    private LogDao logDao;
    
    private Gson gson;
    
    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        gson = JsonTransformer.createGsonWithDateParser();
    }
    
    /**
     * Initialize all the resource methods.
     */
    public void init() {
        addGet();
        addGetAll();
        addGetByDate();
        addGetAmount();
        addGetLast();
        addGetPaged();
        addDelete();
        addPost();
    }
    
    private void addGetPaged() {
        Spark.get("/logs/page/:pageNumber/:amount", "application/json", (req, res) -> {
            int pageNumber = Integer.valueOf(req.params("pageNumber"));
            int amount = Integer.valueOf(req.params("amount"));
            return logDao.findPage(pageNumber, amount);
        }, new JsonTransformer());
    }

    private void addGet() {
        Spark.get("/logs", "application/json", (req, res) -> {
            return logDao.findAll();
        }, new JsonTransformer());
    }
    
    private void addGetAll() {
        Spark.get("/logs/id/:id", "application/json", (req, res) -> {
            return logDao.findById(Long.valueOf(req.params("id")));
        }, new JsonTransformer());
    }
    
    private void addGetByDate() {
        Spark.get("/logs/date/:date", "application/json", (req, res) -> {
            long timeFromEpochSec = Long.valueOf(req.params("date"));
            return logDao.findByDate(new Date(timeFromEpochSec * 1000));
        }, new JsonTransformer());
    }
    
    private void addGetAmount() {
        Spark.get("/logs/amount", "application/json", (req, res) -> {
            String amount = "{ \"amount\" : \"" +  logDao.findAmount() + "\"}"; 
            return amount;
        });
    }

    private void addGetLast() {
        Spark.get("/logs/last", "application/json", (req, res) -> {
            return logDao.findLast();
        }, new JsonTransformer());
    }
    
    private void addPost() {
        Spark.post("/log", "application/json", (req, res) -> {
            String dataToParse = req.body();
            Log newLog = gson.fromJson(new StringReader(dataToParse), Log.class);
            logDao.save(newLog);
            return "done";
        });
    }
    
    private void addDelete() {
        Spark.delete("/log/:id", "application/json", (req, res) -> {
            long logId = Long.valueOf(req.params("id"));
            logDao.delete(logId);
            return "done";
        });
    }
}
