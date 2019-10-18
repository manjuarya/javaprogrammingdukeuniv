
/**
 * Write a description of LogEntry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class LogEntry {
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;
    
    public LogEntry (String ip, Date time, String req, int status, int bytes) {
        ipAddress = ip;
        accessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    public Date getaccessTime() {
        return accessTime;
    }
    
    public String getrequest() {
        return request;
    }
    
    public int getstatusCode() {
        return statusCode;
    }
    
    public int getbytesReturned() {
        return bytesReturned;
    }
    
    public String toString() {
        return ipAddress + "  " + accessTime + "  " + request + "  " + statusCode + "  " + bytesReturned;
    }
    
    
}
