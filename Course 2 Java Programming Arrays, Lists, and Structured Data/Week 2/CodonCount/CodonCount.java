
/**
 * Write a description of class CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
private HashMap <String, Integer> codonMap;
private int maxCount;

public CodonCount(){
    codonMap = new HashMap<String,Integer>();
    maxCount = 0;
}
private void buildCodonMap (int start, String dna){
    codonMap.clear();
    for (int i=start;i<dna.length()-3;i+=3){
     String codon = dna.substring(i,i+3);
     if (codonMap.containsKey(codon)){
         int count = codonMap.get(codon);
         codonMap.put(codon,count+1);
        }
        else{
            codonMap.put(codon,1);
        }
}
    System.out.println(codonMap.toString());
}
private String getMostCommonCodon(){
    String mostCodon = "";
    int maxCount = 0;
    for(String c : codonMap.keySet()){
        int occurrences = codonMap.get(c);
     if (occurrences > maxCount){
         mostCodon = c;
         maxCount = occurrences;
        }
    }
    return mostCodon;
}
private void printCodonCounts (int start, int end){
    for (String c : codonMap.keySet()){
        int count = codonMap.get(c);
        if (count >= start && count <= end){
        System.out.println(c + " " + count);
    }
    }
}
public void tester(){
    FileResource fr = new FileResource();
    String dna = fr.asString();
    dna.toUpperCase();
    System.out.println(dna);
    for (int start = 0; start <= 2; start++) {
    buildCodonMap(start,dna);
    System.out.println("Reading frame starting with " + start + " results in " + codonMap.size() + " unique codons");
    String mostCommonCoddon = getMostCommonCodon();
    System.out.println("and most common codon is " + mostCommonCoddon + " with count " + codonMap.get(mostCommonCoddon));
    System.out.println("Counts of codons between 1 and 5 inclusive are: ");
    printCodonCounts(1, 5);
}
}
}
