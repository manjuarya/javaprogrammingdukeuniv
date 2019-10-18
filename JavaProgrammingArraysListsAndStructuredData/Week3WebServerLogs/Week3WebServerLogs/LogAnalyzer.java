package Week3WebServerLogs;


/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry logLine = WebLogParser.parseEntry(line);
            records.add(logLine);
        }
    }
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public int countUniqueIPs() {
        ArrayList<String> uniqueIps = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIps.contains(ipAddr)) {
                uniqueIps.add(ipAddr);
            }
        }
        System.out.println("There are total unique Ips in this file is  " + uniqueIps.size());
        return uniqueIps.size();
    }
    
    public void printAllHigherThanNum(int num) {
        ArrayList<LogEntry> laHigherThanNum = new ArrayList<LogEntry>();
        
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                laHigherThanNum.add(le);
            }
        }
        
        for (LogEntry le : laHigherThanNum) {
            System.out.println(le);
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIpsInADay = new ArrayList<String>();
        for (LogEntry le : records) {
            Date accessTime = le.getAccessTime();
            String accessTimeStr = accessTime.toString();
            if (accessTimeStr.contains(someday)) {
                String ipAddr = le.getIpAddress();
                if (!uniqueIpsInADay.contains(ipAddr)) {
                    uniqueIpsInADay.add(ipAddr);
                }
            }
        }
        return uniqueIpsInADay;
    }
    
    public void countUniqueIPsInRange(int low, int high) {
        ArrayList<LogEntry> laBetweenLowHigh = new ArrayList<LogEntry>();
        ArrayList<String> uniqueIps = new ArrayList<String>();
        int count=0;
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode >= low && statusCode <= high) {
                laBetweenLowHigh.add(le);
            }
        }
        
        for (LogEntry le : laBetweenLowHigh) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIps.contains(ipAddr)) {
                uniqueIps.add(ipAddr);
            }
        }
        System.out.println("The no of unique ips visit in the range  " + low + "  &  " + high + " is " + uniqueIps.size());
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> perIpCount = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!perIpCount.containsKey(ipAddr)) {
                perIpCount.put(ipAddr, 1);
            }
            else {
                perIpCount.put(ipAddr, perIpCount.get(ipAddr) + 1);
            }
        }
        return perIpCount;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxCount = 0;
        
        for (String ip : counts.keySet()) {
            if (counts.get(ip) > maxCount) {
                maxCount = counts.get(ip);
            }
        }
        return maxCount;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> ipsVisit = new ArrayList<String>();
        int maxCount = 0;
                
        for (String ip : counts.keySet()) {
            if (counts.get(ip) > maxCount) {
                maxCount = counts.get(ip);
            }
        }
        
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == maxCount) {
                ipsVisit.add(ip);
            }
        }
        System.out.println(ipsVisit);
        return ipsVisit;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysWiseDetail = new HashMap<String, ArrayList<String>>();
       
        for (LogEntry le : records) {
            ArrayList<String> ipAddressPerDay = new ArrayList<String>();
            Date accessTime = le.getAccessTime();
            String accessTimeStr = accessTime.toString();
            accessTimeStr = accessTimeStr.substring(4, 10);
            String ipAddress = le.getIpAddress();  
            
            if (!daysWiseDetail.containsKey(accessTimeStr)) { 
                ipAddressPerDay.add(ipAddress);
                daysWiseDetail.put(accessTimeStr, ipAddressPerDay);
            }
            
            else {
                daysWiseDetail.get(accessTimeStr).add(ipAddress);
            }
        }
        return daysWiseDetail;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> perDayIps) {
        ArrayList<String> ipsPerDay = new ArrayList<String>();
        int maxIpsInaDay = 0;
        int i = 0;
        String day = "";
        for (String s : perDayIps.keySet()) {
            i = perDayIps.get(s).size();
            if (maxIpsInaDay < i) {
                maxIpsInaDay = i;
                day = s;
            }
        }
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> perDayIps, String day) {
        HashMap<String, Integer> uniqueIpsPerDay = new HashMap<String, Integer>();
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<String> b = new ArrayList<String>();
        for (String s : perDayIps.keySet()) {
            if (s.equals(day)) {
                a = perDayIps.get(s);
                for (String ip : a) {
                    if(!uniqueIpsPerDay.containsKey(ip)) {
                        uniqueIpsPerDay.put(ip, 1);
                    }
                    else {
                        uniqueIpsPerDay.put(ip, uniqueIpsPerDay.get(ip)+1);
                    }
                }
            }
        }
        
        b = iPsMostVisits(uniqueIpsPerDay);
        return b;
    }
}
