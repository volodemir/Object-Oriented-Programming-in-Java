
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<String> uniqueIPs;
     private ArrayList<Integer> statusCodes;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
         uniqueIPs = new ArrayList<String>();
         statusCodes = new ArrayList<Integer>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource();
         for (String l : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(l);
             records.add(le);
            }
     }
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le: records){
             String ip = le.getIpAddress();
             if (!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else {
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return counts;
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> count){
         int max = 0;
         count = countVisitsPerIP();
         for (Integer v : count.values()) {
         if (max < v){
             max = v;
            }
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> co){
        ArrayList<String> ipMosVisit = new ArrayList<String>();
        int mostNumberVisit = mostNumberVisitsByIP(co);
        for (String s : co.keySet()) {
            if(co.get(s) == mostNumberVisit){
             ipMosVisit.add(s);
            }
    }
    return ipMosVisit;
    }
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ipForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records){
            String date = le.getAccessTime().toString();
            String ipAddr = le.getIpAddress();
            String shortDate = date.substring(4,7) + " " + date.substring(8,10);
        if (!ipForDays.containsKey(shortDate)){
            ArrayList<String> ip = new ArrayList();
            ip.add(ipAddr);
            ipForDays.put (shortDate, ip);
            }
            else {
                ipForDays.get(shortDate).add(ipAddr);
            }
        }
        return ipForDays;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipForDays){
        int maxCount = 0;
        String dayMostVisit = "";
            for (String day : ipForDays.keySet()){
                int count = ipForDays.get(day).size();
                if (count > maxCount){
                    maxCount = count;
                    dayMostVisit = day;
            }
        }
        return dayMostVisit;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> map, String day){
        ArrayList<String> ip = new ArrayList <String>();
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for (LogEntry le: records){
             String ipAddr = le.getIpAddress();
             String date = le.getAccessTime().toString();
             String shortDate = date.substring(4,7) + " " + date.substring(8,10);
             if (shortDate.equals(day)){
             if (!counts.containsKey(ipAddr)){
                 counts.put(ipAddr,1);
             }
             else {
                 counts.put(ipAddr,counts.get(ipAddr)+1);
             }
            
            }
        }
            int max = 0;
            for (Integer v : counts.values()) {
         if (max < v){
             max = v;
            }
        }
        for (String s : counts.keySet()) {
            if(counts.get(s) == max){
             ip.add(s);
            }
        
        }  
        return ip;
    }
     public int countUniqueIPs(){ 
         for (LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
                 }
            }
            return uniqueIPs.size();
        }
     public int uniqueIPVisitsOnDay (String someday){
            uniqueIPs.clear();
            for (LogEntry le: records){
             String date = le.getAccessTime().toString();
             String ipAddr = le.getIpAddress().toString();
             if (someday.substring(0,3).equals(date.substring(4,7)) && someday.substring(4).equals(date.substring(8,10))){
                 if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
                 }
            }
            }
            return uniqueIPs.size();
        }
     public int countUniqueIPsInRange (int low, int high){
            uniqueIPs.clear();
            statusCodes.clear();
            for (LogEntry le: records){
                String ipAddr = le.getIpAddress().toString();
                int sc = le.getStatusCode();
                if (sc >= low && sc <= high)
                {
                    if (!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
                }
            }
            return uniqueIPs.size();
        }
     public void printAllHigherThanNum(int num){
         statusCodes.clear();
         for (LogEntry le: records){
             int sc = le.getStatusCode();
             if (sc > num){
                 statusCodes.add(sc);
                 System.out.println(le);
                }
             }
        }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
