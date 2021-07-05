
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
public String findGene (String dna, int Index){
    int startIndex = dna.indexOf("ATG", Index);
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
public int countGenes (String dna){
    int startIndex = 0;
    int count = 0;
    while (true){
        String currentGene = findGene(dna, startIndex);
        if(currentGene.isEmpty()){
            break;
}
        //System.out.println(currentGene);
        count ++;
        startIndex = dna.indexOf (currentGene, startIndex) + currentGene.length();
}
return count;
}
public void testCountGenes (){
    String str = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
    int count = countGenes(str);
    System.out.println ("Количество генов в ДНК " + str + " " + count);
    
    str = "ATGATCATAAGAAGATAATAGAGGGCCTAA";
    count = countGenes(str);
    System.out.println ("Количество генов в ДНК " + str + " " + count);
}
}
