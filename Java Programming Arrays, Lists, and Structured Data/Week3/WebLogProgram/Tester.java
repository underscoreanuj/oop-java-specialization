
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        // la.printAllHigherThanNum(400);
        // ArrayList<String> result1 = la.uniqueIPVisitsOnDay("Mar 17");
        // System.out.println(result1.size());
        // int result = la.countUniqueIPsInRange(300, 399);
        // System.out.println(result);
        
        
        // HashMap<String, ArrayList<String>> res = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29"));
        
    }
}
