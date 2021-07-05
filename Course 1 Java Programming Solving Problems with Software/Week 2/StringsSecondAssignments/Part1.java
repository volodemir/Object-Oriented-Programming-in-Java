
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex);
        
        while (currIndex != -1)
        {
         int diff = currIndex - startIndex;
         if (diff % 3 == 0) {
             return currIndex;
             
        }
        else {
         currIndex = dna.indexOf(stopCodon, currIndex + 1);   
        }
        
    }
    return dna.length();
}
public String findGene (String dna){
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1){
    return "";
    }
    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");
    
    int minIndex = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
    if (minIndex == dna.length()){
     return "";   
    }
    else{
    return dna.substring(startIndex, minIndex + 3);
}
}
    public void testFindStopCodon(){
        String dna = "qqqwwwqqqATGqqqwwwATGqq";
        int dex = findStopCodon(dna,0,"ATG");
        System.out.println(dex);
        
        //     0123456789012345678901
        dna = "qqqwwwqqATGqqqwwwATGqq";
        dex = findStopCodon(dna,0,"ATG");
        System.out.println(dex);   
        
}
public void printAllGenes(String dna){
    while (true){
        String currentGene = findGene(dna);
        if(currentGene.isEmpty()){
            break;
}
System.out.println(currentGene);
}
}
public void testFindGene(){
    String dna = "qqqwwwqqqwwwqqqwwwqqq";
    String gen = findGene(dna);
    System.out.println(gen);
    
    dna = "qqqATGwwwqqqwwwqqqTAAwwwqqq";
    gen = findGene(dna);
    System.out.println(gen);
    
    dna = "qqqATGwwwqqqTAGwwwqqqTAAwwwTGAqqq";
    gen = findGene(dna);
    System.out.println(gen);
    
    dna = "qqqATGwwwqqqwwwqqqwwwqqq";
    gen = findGene(dna);
    System.out.println(gen);
}
}
