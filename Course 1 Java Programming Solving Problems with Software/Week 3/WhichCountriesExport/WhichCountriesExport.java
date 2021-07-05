/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
     public void countryInfo (CSVParser parser, String exportOfInterest){
         for (CSVRecord record : parser) {
            
            String export = record.get("Country");
           
            if (export.contains(exportOfInterest)) {
               System.out.print(record.get("Country") + ": ");
               System.out.print(record.get("Exports") + ": ");
               System.out.println(record.get("Value (dollars)") + ": ");
            }
            else {
                System.out.println("NOT FOUND");
            }
        }
        }
        public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
       }
      public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
          for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
}
public void tester2() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts (parser, "cotton", "flowers");
       }
      public void numberOfExporters (CSVParser parser, String exportItem){
          int total = 0;
          for (CSVRecord record : parser) {
            int count = 0;
            
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count ++;
            }
            total += count;
        }
        System.out.println("Количество стран, экспортирующих " + exportItem + ": " + total);
        }
        public void tester3() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        numberOfExporters (parser, "cocoa");
       }
       public void bigExporters (CSVParser parser, String amount){
           for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
           
            if (value.length() > 16) {
               System.out.print(record.get("Country") + ": ");
               System.out.println(record.get("Value (dollars)") + ": ");
        }
        }
    }
       public void tester4() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters (parser, "$999,999,999,999");
       }
    }