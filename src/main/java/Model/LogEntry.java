package Model;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.time.LocalDateTime;

public class LogEntry {
    private LocalDateTime logTime;
    private LogLevel logLevel;
    private String beforeMessage;
    private String afterMessage;
    private String userId;
    private String sessionId;
    private String ipAddress;
//    private String location;

    public LogEntry( LogLevel logLevel, String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress) {
        this.logTime = LocalDateTime.now();
        this.logLevel = logLevel;
        this.beforeMessage = beforeMessage;
        this.afterMessage = afterMessage;
        this.userId = userId;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;


    }

    public LogEntry(LocalDateTime logTime, LogLevel logLevel, String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress) {
        this.logTime = logTime;
        this.logLevel = logLevel;
        this.beforeMessage = beforeMessage;
        this.afterMessage = afterMessage;
        this.userId = userId;
        this.sessionId = sessionId;
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getBeforeMessage() {
        return beforeMessage;
    }

    public void setBeforeMessage(String beforeMessage) {
        this.beforeMessage = beforeMessage;
    }

    public String getAfterMessage() {
        return afterMessage;
    }

    public void setAfterMessage(String afterMessage) {
        this.afterMessage = afterMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
}
