package Model;

public interface ILog {
    void log(LogEntry entry);
     void info(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress);
    void warn(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress);
    void error(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress);
    void alter(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress);
}
