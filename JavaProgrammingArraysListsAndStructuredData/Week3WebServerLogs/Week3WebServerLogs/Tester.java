package Week3WebServerLogs;

 


/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class Tester {
    HashMap<String, Integer> counts = new HashMap<String, Integer>();
    
    
    public void testLogEntry() {
        LogEntry le = new LogEntry ("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        
        LogEntry le2 = new LogEntry ("1.2.100.4", new Date(), "example request2", 300, 400);
        System.out.println(le2);
        
        
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la1 = new LogAnalyzer();
        la1.readFile("./Data/weblog2_log.txt");
        la1.countUniqueIPs();
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la2 = new LogAnalyzer();
        la2.readFile("./Data/weblog1_log.txt");
        la2.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        ArrayList<String> ipsInADay = new ArrayList<String>();
        LogAnalyzer la3 = new LogAnalyzer();
        la3.readFile("./Data/weblog1_log.txt");
        ipsInADay = la3.uniqueIPVisitsOnDay("Mar 24");
        for (int k=0; k<ipsInADay.size(); k++) {
            System.out.println(ipsInADay.get(k));
        }
        System.out.println(ipsInADay.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la4 = new LogAnalyzer();
        la4.readFile("./Data/weblog1_log.txt");
        la4.countUniqueIPsInRange(300, 399);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/weblog1_log.txt");
        counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP () {
        int max=0;
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/weblog1_log.txt");
        counts = la.countVisitsPerIP();
        max = la.mostNumberVisitsByIP(counts);
        System.out.println("The max times the site visit by an ip is  " + max);
    }
    
    public void testIpsMostVisits() {
        ArrayList<String> ipsmstVisits = new ArrayList<String>();
        LogAnalyzer la = new LogAnalyzer();
        testCountVisitsPerIP();
        ipsmstVisits = la.iPsMostVisits(counts);
        for (int k=0; k<ipsmstVisits.size(); k++) {
            System.out.println(ipsmstVisits.get(k));
        }
    }
    
    public void testIPsForDays() {
        HashMap<String, ArrayList<String>> perDayIps = new HashMap<String, ArrayList<String>>();
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/weblog1_log.txt");
        perDayIps = la.iPsForDays();
        for(String s : perDayIps.keySet()) {
            System.out.println(s + "  " + perDayIps.get(s));
        }
    }
    
    public void testDayWithMostIPVisits() {
        HashMap<String, ArrayList<String>> perDayIps = new HashMap<String, ArrayList<String>>();
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/weblog1_log.txt");
        perDayIps = la.iPsForDays();
        String day = la.dayWithMostIPVisits(perDayIps);
        System.out.println(day);
    }
    
    public void testIPsWithMostVisitsOnDay() {
        HashMap<String, ArrayList<String>> perDayIps = new HashMap<String, ArrayList<String>>();
        ArrayList<String> uniqueIpsWithMostVisitOnDay = new ArrayList<String>();
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/weblog1_log.txt");
        perDayIps = la.iPsForDays();
        uniqueIpsWithMostVisitOnDay = la.iPsWithMostVisitsOnDay(perDayIps, "Mar 17");
        
        System.out.println(uniqueIpsWithMostVisitOnDay);
    }
}
