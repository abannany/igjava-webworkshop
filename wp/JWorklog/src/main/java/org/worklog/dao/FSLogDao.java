package org.worklog.dao;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Default;

import org.apache.commons.io.FileUtils;
import org.worklog.model.Log;
import org.worklog.utils.StringUtils;

@Default
public class FSLogDao implements LogDao {

    private static final File logDir = new File("data");

    @Override
    public Log findById(long id) {
        for (File logFile : logDir.listFiles()) {
            if (logFile.getName().startsWith(id + "_")) {
                Log newLog = new Log();
                newLog.setDate(getDate(logFile.getName()));
                newLog.setId(id);
                try {
                    newLog.setLogText(FileUtils.readFileToString(logFile));
                } catch (IOException e) {
                    throw new IllegalStateException("Can't read content of the file " + logFile.getName(), e);
                }
                return newLog;
            }
        }
        return null;
    }

    @Override
    public Log findByDate(Date date) {
        for (File logFile : logDir.listFiles()) {
            Date logDate = getDate(logFile.getName());
            if (logDate.compareTo(date) == 0) {
                Log newLog = new Log();
                newLog.setDate(logDate);
                newLog.setId(getId(logFile.getName()));
                try {
                    newLog.setLogText(FileUtils.readFileToString(logFile));
                } catch (IOException e) {
                    throw new IllegalStateException("Can't read content of the file " + logFile.getName(), e);
                }
                return newLog;
            }
        }
        return null;
    }

    @Override
    public List<Log> findByDateBetween(Date start, Date end) {
        List<Log> logs = new ArrayList<>();
        for (File logFile : logDir.listFiles()) {
            Date logDate = getDate(logFile.getName());
            if (logDate.after(start) && logDate.before(end)) {
                Log newLog = new Log();
                newLog.setDate(logDate);
                newLog.setId(getId(logFile.getName()));
                try {
                    newLog.setLogText(FileUtils.readFileToString(logFile));
                } catch (IOException e) {
                    throw new IllegalStateException("Can't read content of the file " + logFile.getName(), e);
                }
                logs.add(newLog);
            }
        }
        return logs;
    }

    @Override
    public void save(Log log) {
        if (log.getId() == -1) {
            log.setId(findMaxId() + 1);
        } else {
            delete(log.getId());
        }
        File logFile = new File(logDir, getFilename(log));
        try {
            FileUtils.write(logFile, log.getLogText());
        } catch (IOException e) {
            throw new IllegalStateException("Can't write the file", e);
        }
    }

    @Override
    public void update(Log log) {
        save(log);
    }

    @Override
    public void delete(long logId) {
        Log existingLog = findById(logId);
        if (existingLog != null) {
            File exFile = new File(logDir, getFilename(existingLog));
            exFile.delete();
        }
    }

    @Override
    public List<Log> findAll() {
        List<Log> logs = new ArrayList<>();
        for (File logFile : logDir.listFiles()) {
            Date logDate = getDate(logFile.getName());
            Log newLog = new Log();
            newLog.setDate(logDate);
            newLog.setId(getId(logFile.getName()));
            try {
                newLog.setLogText(FileUtils.readFileToString(logFile));
            } catch (IOException e) {
                throw new IllegalStateException("Can't read content of the file " + logFile.getName(), e);
            }
            logs.add(newLog);
        }
        return logs;
    }

    @Override
    public Log findLast() {
        long maxId = findMaxId();
        return findById(maxId);
    }

    public long findMaxId() {
        long maxId = 0;
        for (File logFile : logDir.listFiles()) {
            long currentId = getId(logFile.getName());
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId;

    }

    @Override
    public long findAmount() {
        long amountOfFile = 0;
        for (File logFile : logDir.listFiles()) {
            if (isLogFile(logFile)) {
                amountOfFile++;
            }
        }
        return amountOfFile;
    }

    @Override
    public List<Log> findPage(int pageNumber, int amount) {
        List<Long> ids = new ArrayList<>();
        for (File logFile : logDir.listFiles()) {
            if (isLogFile(logFile)) {
                ids.add(getId(logFile.getName()));
            }
        }
        Collections.sort(ids);
        int start = (pageNumber - 1) * amount;
        ids = ids.subList(start, start + amount);
        List<Log> logs = new ArrayList<>();
        for (Long id : ids) {
            logs.add(findById(id));
        }
        return logs;
    }

    private boolean isLogFile(File logFile) {
        return logFile.getName().matches("\\d{1,}_\\d{4}(_\\d{2}){5}\\.log");
    }

    private String getFilename(Log log) {
        StringBuilder sb = new StringBuilder();
        sb.append(log.getId()).append("_");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        sb.append(dateFormat.format(log.getDate())).append(".log");
        return sb.toString();
    }

    private long getId(String filename) {
        return Long.valueOf(filename.split("_")[0]);
    }

    private Date getDate(String filename) {
        String datePart = StringUtils.getSubstring("\\d{4}(_\\d{2}){5}", filename);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        try {
            return dateFormat.parse(datePart);
        } catch (ParseException e) {
            throw new IllegalStateException("Can't parse the date pare of the filename", e);
        }
    }
}
