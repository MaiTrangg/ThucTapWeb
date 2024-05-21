package DAO;

import DBConnection.JDBCUtil;
import Model.Geo_API;
import Model.ILog;
import Model.LogEntry;
import Model.LogLevel;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogDao implements ILog {
    Connection  con = null;
    PreparedStatement pst = null;

    @Override
    public List<LogEntry> getAllLog() {
        List<LogEntry> logs= new ArrayList<>();

        String query = "select * from logs";


        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                //chuyen gia tri trong co so du lieu ve LocalDateTime
                Timestamp time = (Timestamp) rs.getObject("logTime");
                LocalDateTime logTime = time.toLocalDateTime();

                String logTypeString = rs.getString("logLevel");
                LogLevel logLevel = LogLevel.valueOf(logTypeString);

                logs.add(
                        new LogEntry(logTime,
                                logLevel,
                                rs.getString("beforeMessage"),
                                rs.getString("afterMessage"),
                                rs.getString("userId"),
                                rs.getString("sessionId"),
                                rs.getString("ipAddress")

                        ));

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return logs;
    }

    @Override
    public void log(LogEntry entry)  {
        String query = "INSERT INTO logs( logTime, logLevel, beforeMessage,afterMessage,userId,sessionId, ipAddress) VALUES(?,?,?,?,?,?,?)";


        try {
            con = JDBCUtil.getConnection();
            pst = con.prepareStatement(query);
            pst.setObject(1, entry.getLogTime());
            pst.setString(2, entry.getLogLevel().name());
            pst.setString(3, entry.getBeforeMessage());
            pst.setString(4, entry.getAfterMessage());
            pst.setString(5, entry.getUserId());
            pst.setString(6, entry.getSessionId());
            pst.setString(7, entry.getIpAddress());
//            pst.setString(8, entry.getLocation());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void info(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress) {
        log(new LogEntry(LogLevel.INFO,  beforeMessage,  afterMessage,  userId,  sessionId,  ipAddress));
    }

    @Override
    public void warn(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress) {
        log(new LogEntry(LogLevel.WARN,  beforeMessage,  afterMessage,  userId,  sessionId,  ipAddress));
    }

    @Override
    public void error(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress) {
        log(new LogEntry(LogLevel.ERROR,  beforeMessage,  afterMessage,  userId,  sessionId,ipAddress));
    }

    @Override
    public void alter(String beforeMessage, String afterMessage, String userId, String sessionId, String ipAddress) {
        log(new LogEntry(LogLevel.ALTER,  beforeMessage,  afterMessage,  userId,  sessionId,  ipAddress));
    }

    public static void main(String[] args) {
        new LogDao().alter("chưa đăng nhập","đã dăng nhập thành công","11","11","11");
    }

//    public String getLocation(String ip){
//        String location ="";
//        try {
//            location = new Geo_API().getLocation(ip);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (GeoIp2Exception e) {
//            throw new RuntimeException(e);
//        }
//        return location;
//    }
}
