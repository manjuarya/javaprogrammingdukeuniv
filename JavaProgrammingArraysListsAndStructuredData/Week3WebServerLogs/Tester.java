
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
    public void testLogEntry() {
        LogEntry le = new LogEntry ("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        
        LogEntry le2 = new LogEntry ("1.2.100.4", new Date(), "example request2", 300, 400);
        System.out.println(le2);
        
        
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("./Data/shortTestLog");
        la.printAll();
    }
}
