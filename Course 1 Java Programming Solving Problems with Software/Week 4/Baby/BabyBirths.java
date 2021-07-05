/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Optional;
import java.util.Comparator; 
import java.util.stream.Collectors;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numberOfGirlsNames = 0;
        int numberOfBoysNames = 0;
        int totalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames ++ ;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                numberOfBoysNames ++ ;
            }
            else {
                totalGirls += numBorn;
                numberOfGirlsNames ++ ;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("number Of Girls Names = " + numberOfGirlsNames);
        System.out.println("number Of Boys Names = " + numberOfBoysNames);
        System.out.println("total Names = " + totalNames);
    }
    
    public int getRank (int year, String name, String gender) throws IOException {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());

        // sort list by rank
        babies.sort((o1, o2) -> {
            if (Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });

        int rank = -1;

        for (CSVRecord rec : babies) {
        
        if (rec.get(0).equals(name))
        rank = babies.indexOf(rec) + 1;
    }
        return rank;
    }
    public int getRankF (int year, String gender) throws IOException{
        FileResource fr = new FileResource("us_babynames_test/yob" + year + "short.csv");
        CSVParser parser = fr.getCSVParser(false);
        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());

        // sort list by rank
        babies.sort((o1, o2) -> {
            if (Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });

        int rank = -1;

        for (CSVRecord rec : babies) {
        
        if (rec.get(1).equals(gender))
        rank = babies.indexOf(rec) + 1;
    }
        return rank;
        } 
       
       public String getName (int year, int rank, String gender) throws IOException{
         FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());

        // sort list by rank
        babies.sort((o1, o2) -> {
            if (Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });
        int CurrRank = -1;
        String name = "NO NAME";
        for (CSVRecord rec : babies){
                if (rec.get(1).equals(gender)) {
                CurrRank = babies.indexOf(rec) + 1;
                if (rank == CurrRank){
                name = rec.get(0);
                }}
            }
         return name;  
        }
        
        public void whatIsNameInYear (String name, int year, int newYear, String gender) throws IOException{
        int rankOfName = getRank(year, name, gender);
        String newName = getName(newYear, rankOfName, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
        }
        
        public int yearOfHighestRank (String name, String gender) throws IOException{
        int currentMaxRank = -1;
        int currentMaxRankYear = -1;
        DirectoryResource dr = new DirectoryResource();
           for (File f : dr.selectedFiles()){
               FileResource fr = new FileResource(f);
               CSVParser parser = fr.getCSVParser(false);
               String currentYear = f.getName().substring(3,7);
               int currYear = Integer.parseInt(currentYear);    
               int rank = getRank(currYear,name, gender);
               /*for(CSVRecord record : parser){
                 if (record.get(0).equals(name)){
                     if (record.get(1).equals(gender)){
                         rank ++;
                         break;
                        }
                }
               }*/
              if (currentMaxRank == -1 &&  rank != -1){
                   currentMaxRank = rank;
                   currentMaxRankYear = currYear;
                }
                   else if (rank < currentMaxRank && rank != -1){
                       currentMaxRank = rank;
                       currentMaxRankYear = currYear;
                    }
        }
        return currentMaxRankYear;
    }
    public double getAverageRank (String name, String gender) throws IOException{
        double avgRank = 0;
        int count = 0;
        int sumRank = 0;
        DirectoryResource dr = new DirectoryResource();
           for (File f : dr.selectedFiles()){
               FileResource fr = new FileResource(f);
               CSVParser parser = fr.getCSVParser(false);
               String currentYear = f.getName().substring(3,7);
               int currYear = Integer.parseInt(currentYear);
               int rank = getRank(currYear,name, gender);
               count ++;
               if (rank != -1){
                   sumRank += (double)rank;
                }
                if (sumRank != 0){
                 avgRank = sumRank / (double)count;   
                } 
    }
    return avgRank;
}
public int getTotalBirthsRankedHigher (int year, String name, String gender) throws IOException{
    FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());

        // sort list by rank
        babies.sort((o1, o2) -> {
            if (Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });
        int totalHighBirths = 0;
        int rank = getRank (year, name, gender);
        int CurrRank = 0;
        
        for (CSVRecord rec : babies) {
        if (rec.get(1).equals(gender)){
            CurrRank = babies.indexOf(rec) + 1;
            if (CurrRank < rank){
        totalHighBirths += Integer.parseInt(rec.get(2));
        }
    }
    }
        return totalHighBirths;
}
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    public void testGetRank () throws IOException {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        System.out.println("rank of the name in the file for the given gender: " + getRank (year, name, gender));
       }
      public void testGetRankF () throws IOException {
           System.out.println("rank of the name in the file for the given gender: " + getRankF (2013, "M"));
        }
       public void testGetName() throws IOException {
           System.out.println("the name of the person in the file at this rank, for the given gender: " + getName (1980, 350, "F"));
        }
        public void testWhatIsNameInYear () throws IOException{
            whatIsNameInYear("Owen", 1974, 2014, "M");
        }
        public void testYearOfHighestRank() throws IOException{
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    public void testGetAverageRank () throws IOException{
   // System.out.println(getAverageRank("Mason", "M"));
    System.out.println(getAverageRank("Robert", "M"));
    }
    public void testGetTotalBirthsRankedHigher() throws IOException{
        System.out.println("Общее число рожденных с именами, имеющими больший ранг: " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}

