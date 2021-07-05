
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1){
          return "";
}
        int stopCodon = dna.indexOf("TAA", startCodon + 3);
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
    String gene = findSimpleGene(dna);
    System.out.println ("Ген: " + gene);
    
    dna = "ATGACATTGACTGTTA";
    System.out.println ("ДНК общее: " + dna);
    gene = findSimpleGene(dna);
    System.out.println ("Ген: " + gene);
    
    dna = "ACATTGACTGTTA";
    System.out.println ("ДНК общее: " + dna);
    gene = findSimpleGene(dna);
    System.out.println ("Ген: " + gene);
    
    dna = "ATGACATTGACTG";
    System.out.println ("ДНК общее: " + dna);
    gene = findSimpleGene(dna);
    System.out.println ("Ген: " + gene);
}
}