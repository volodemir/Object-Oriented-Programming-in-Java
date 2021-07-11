
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
        la.readFile("short-test_log");
        la.printAll();
    }
	public void testCounts(){
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblog3-short_log");
		HashMap<String,Integer> counts = la.countVisitsPerIP();
		//System.out.println(counts);
		System.out.println("the maximum number of visits to this website by a single IP address: " + la.mostNumberVisitsByIP(counts));
		System.out.println("List of Strings of IP addresses that all have the maximum number of visits to this website " + la.iPsMostVisits(counts));
		System.out.println(la.iPsForDays());
		HashMap<String, ArrayList<String>> ipForDays = la.iPsForDays();
		System.out.println("the day that has the most IP address visits: " + la.dayWithMostIPVisits(ipForDays));
		System.out.println("List of IP addresses that had the most accesses on the Sep 29: " + la.iPsWithMostVisitsOnDay(ipForDays, "Sep 29"));
	}
}
