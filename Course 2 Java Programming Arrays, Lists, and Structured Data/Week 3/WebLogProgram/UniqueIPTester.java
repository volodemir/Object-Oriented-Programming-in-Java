
/**
 * Write a description of class UniqueIPTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueIPTester {
public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog-short_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
        System.out.println("LogEntrys that have a status code greater than 200: ");
        la.printAllHigherThanNum(400);
        System.out.println("unique IP addresses that had access on the Sep 30");
        System.out.println("unique IP on day: " + la.uniqueIPVisitsOnDay("Sep 27"));
        
        int low = 200;
        int high = 299;
        System.out.println("the number of unique IP addresses that have a status code in the range from " + low + " to " + high + " is: " + la.countUniqueIPsInRange(low,high));
}

}
