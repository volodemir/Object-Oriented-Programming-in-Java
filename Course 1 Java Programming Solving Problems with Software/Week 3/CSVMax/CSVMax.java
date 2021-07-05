/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            //If largestSoFar is nothing
                largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
public CSVRecord coldestHourInFile(CSVParser parser) {
    CSVRecord leastestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            double leastest = Double.parseDouble(currentRow.get("TemperatureF"));
            if (leastest == -9999) continue;
            else {
                leastestSoFar = getLeastestOfTwo(currentRow, leastestSoFar);
        }
    }
        return leastestSoFar;
}
    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
    public void testColdestInDay () {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord leastest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + leastest.get("TemperatureF") +
                   " at " + leastest.get("TimeEDT"));
    }
    public String fileWithColdestTemperature (){
        CSVRecord leastestTemp = null;
        DirectoryResource dr = new DirectoryResource();
        File filename = null;
        double coldestTempSoFar = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
           
        if (leastestTemp == null){
            filename = f;
            leastestTemp = coldestHourInFile(fr.getCSVParser());
            coldestTempSoFar = Double.parseDouble(leastestTemp.get("TemperatureF"));
    }
    else {
        CSVRecord coldestCurrent = coldestHourInFile(fr.getCSVParser());
        double currentTemp = Double.parseDouble(coldestCurrent.get("TemperatureF"));
        coldestTempSoFar = Double.parseDouble (leastestTemp.get("TemperatureF"));
        
        if (currentTemp < coldestTempSoFar){
        filename = f;
        leastestTemp = coldestCurrent;
        }
        }
        
    }
    System.out.println("Coldest day was in file " + filename.getName());
    System.out.println("Coldest temperature on that day was " + leastestTemp.get("TemperatureF"));
    System.out.println("All the Temperatures on the coldest day were:");
    FileResource fr = new FileResource(filename);
    CSVParser parser = fr.getCSVParser();
    
    for(CSVRecord record : parser){
        System.out.println(record.get("DateUTC")+ ": " + record.get("TemperatureF"));
    }
    return filename.getName();
}

public CSVRecord lowestHumidityInFile (CSVParser parser){
    CSVRecord lowestSoFar = null;
    
    for (CSVRecord record : parser){
     if (record.get("Humidity").equals("N/A")) continue;
     
     if (lowestSoFar == null) lowestSoFar = record;
     else {
         double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
         double humidityCurrent = Double.parseDouble(record.get("Humidity"));
         
         if (lowestHumidity > humidityCurrent) lowestSoFar = record;
        }
    }
    return lowestSoFar;
}
public CSVRecord lowestHumidityInManyFiles (){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser p = fr.getCSVParser();
            CSVRecord current = lowestHumidityInFile(p);
            
           if (lowest == null) lowest = current;
           else {
               double currentHum = Double.parseDouble(current.get("Humidity"));
               double lowestHum = Double.parseDouble(lowest.get("Humidity"));
               
               if (currentHum < lowestHum) lowest = current;
            }
        }
        System.out.println("lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
        return lowest;
}
public double averageTemperatureInFile (CSVParser parser) {
    int count = 0;
    double sum = 0.0;
    
    for (CSVRecord record : parser){
        sum += Double.parseDouble(record.get("TemperatureF"));
        count ++;
    }
    double avgTemp = sum / count;
    System.out.println("Average temperature in file is " + avgTemp);
    return avgTemp;
}
public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
    int count = 0;
    double sum = 0.0;
    double avgTemp = 0.0;
    
    for (CSVRecord record : parser){
        int currentHum = Integer.parseInt(record.get("Humidity"));
        
        if(currentHum >= value) count ++; sum += Double.parseDouble(record.get("TemperatureF"));
    }
    if (count == 0) {System.out.println("No temperatures with that humidity");}
        avgTemp = sum / count;
        System.out.println("Average Temp when high Humidity is " + avgTemp);
        return avgTemp;
}
public void testAverageTemperatureInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double avgTemp = averageTemperatureInFile(parser);    
}
public void testAverageTemperatureWithHighHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
}
public void testLowestHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord csv = lowestHumidityInFile(parser);
    System.out.println("lowest humidity was " + csv.get("Humidity") + " at " + csv.get ("DateUTC"));
}
public void testLowestHumidityInManyFiles() {
   // FileResource fr = new FileResource();
   // CSVParser parser = fr.getCSVParser();
    CSVRecord csv = lowestHumidityInManyFiles();
}
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }        //The largestSoFar is the answer
        return largestSoFar;
    }
    public CSVRecord coldestInManyDays() {
        CSVRecord leastestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            leastestSoFar = getLeastestOfTwo(currentRow, leastestSoFar);
        }
        
        return leastestSoFar;
    }
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar){
        if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp > largestTemp) {
                    //If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
                }
            }
            return largestSoFar;
}
     public CSVRecord getLeastestOfTwo (CSVRecord currentRow, CSVRecord leastestSoFar){
         if (leastestSoFar == null) {
                leastestSoFar = currentRow;
            }
            
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double leastestTemp = Double.parseDouble(leastestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < leastestTemp) {
                    //If so update largestSoFar to currentRow
                    leastestSoFar = currentRow;
                }
            }
            return leastestSoFar;
        }
    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    public void testColdestInManyDays () {
        CSVRecord leastest = coldestInManyDays();
        System.out.println("leastest temperature was " + leastest.get("TemperatureF") +
                   " at " + leastest.get("DateUTC"));
    }
}
