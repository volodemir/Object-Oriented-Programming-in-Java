
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
public String findSimpleGene(String dna, int startCodon, int stopCodon){
        startCodon = dna.indexOf("ATG");
        if (startCodon == -1){
          return "";
}
        stopCodon = dna.indexOf("TAA", startCodon + 3);
        if (stopCodon == -1){
          return "";
        }
        String result = dna.substring(startCodon, stopCodon+3);
        if ((stopCodon-startCodon) % 3 == 0){
            return result;
}
return result;
}
public void testSimpleGene(){
    String dna = "AATGCGTAATATGGT";
    System.out.println ("ДНК общее: " + dna);
    int startCodon = dna.indexOf("ATG");
    int stopCodon = dna.indexOf("TAA", startCodon + 3);
    String gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println ("Ген: " + gene);
    
    dna = "ATGACATTGACTGTTCGA";
    System.out.println ("ДНК общее: " + dna);
    startCodon = dna.indexOf("ATG");
    stopCodon = dna.indexOf("TAA", startCodon + 3);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println ("Ген: " + gene);
    
    dna = "ACATTGACTGTTAAA";
    System.out.println ("ДНК общее: " + dna);
    startCodon = dna.indexOf("ATG");
    stopCodon = dna.indexOf("TAA", startCodon + 3);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println ("Ген: " + gene);
    
    dna = "atgacattgactaac";
    System.out.println ("ДНК общее: " + dna);
    startCodon = dna.indexOf("ATG");
    stopCodon = dna.indexOf("TAA", startCodon + 3);
    gene = findSimpleGene(dna, startCodon, stopCodon);
    System.out.println ("Ген: " + gene);
}
}
