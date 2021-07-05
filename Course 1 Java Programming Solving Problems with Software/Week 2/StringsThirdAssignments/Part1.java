
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

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
public String findGene (String dna, int where){
    int startIndex = dna.indexOf("ATG", where);
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
public int countGenes60 (String dna){
    int startIndex = 0;
    int count = 0;
    while (true){
        String currentGene = findGene(dna, startIndex);
        if(currentGene.isEmpty()){
            break;
}
if(currentGene.length()>60){
count ++;
}
        //System.out.println(currentGene);
        
        startIndex = dna.indexOf (currentGene, startIndex) + currentGene.length();
}
return count;
}
public int countGenes035 (String dna){
    int startIndex = 0;
    int count = 0;
    while (true){
        String currentGene = findGene(dna, startIndex);
        if(currentGene.isEmpty()){
            break;
}
if(cgRatio(currentGene) > 0.35){
count ++;
}
        //System.out.println(currentGene);
        
        startIndex = dna.indexOf (currentGene, startIndex) + currentGene.length();
}
return count;
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
    int startIndex = 0;
    while (true){
        String currentGene = findGene(dna, startIndex);
        if(currentGene.isEmpty()){
            break;
}
System.out.println(currentGene);
startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
}
}
public StorageResource getAllGenes(String dna){
    StorageResource geneList = new StorageResource();
    int startIndex = 0;
    while (true){
        String currentGene = findGene(dna, startIndex);
        if(currentGene.isEmpty()){
            break;
        }
        geneList.add(currentGene);
        startIndex = dna.indexOf (currentGene,startIndex) +
        currentGene.length(); 
}
return geneList;
}
public float cgRatio (String dna){
    int firstOccurC = dna.indexOf("C");
    int firstOccurG = dna.indexOf("G");
    int countC = 0;
    int countG = 0;
    
    if(firstOccurC > -1){
       countC += 1;
       while (dna.indexOf("C", firstOccurC) != -1 && firstOccurC != -1){
           countC += 1;
           firstOccurC = dna.indexOf("C", firstOccurC + 1);
        }
        countC -= 1;
    }
    else { countC = 0; }
    
    if(firstOccurG > -1){
       countG += 1;
       while (dna.indexOf("G", firstOccurG) != -1 && firstOccurG != -1){
           countG += 1;
           firstOccurG = dna.indexOf("G", firstOccurG + 1);
        }
       countG -= 1;
    }
    else { countG = 0; }
    float result = ((float)countC + countG) / dna.length();
    return result;
}
public int countCTG (String dna){
    int startIndex = 0;
    int count = 0;
    
    startIndex = dna.indexOf("CTG", startIndex);
    if (startIndex == -1){
    return 0;
   
}
   while (startIndex != -1){
   count += 1;
   startIndex = dna.indexOf("CTG", startIndex + 3);
}
return count;
}
public void processGenes (StorageResource sr){
    for(String g:sr.data()){
        if(g.length() > 9){
        System.out.println(g);}
    }
    
    int count = 0;
    for(String g:sr.data()){
        if(g.length() > 9)
        {
        count += 1;
    }
}
System.out.println(count);
    for(String g:sr.data()){
        if (cgRatio(g) > 0.35){
            System.out.println(g);
        }
    }
    count = 0;
    for(String g:sr.data()){
        if (cgRatio(g) > 0.35){
            count += 1;            
    }
}
System.out.println(count);

int geneLength = 0;
String gene = "";
for(String g:sr.data()){
    if (g.length() > geneLength){
        geneLength = g.length();
    }
}
System.out.println(geneLength);
}
public void processGenes2 (StorageResource sr){
    for (String g : sr.data()){
        //печать содержимого файла
        System.out.println("what you see " + g);
        printAllGenes(g);
        
        //ищем общее количество генов
        //задаем счетчик
        int count = 0;
        count = countGenes(g);
        System.out.println("Количество генов: " + count);
        //поиск гена
        String gene = findGene(g, 0);
        //поиск гена длиной более 60 символов
        if(gene.length() > 60){
        System.out.println("Ген с набором символов более 60 шт.: " + gene);}
        
        //сбрасываем счетчик
        count = 0;
        //считаем количество генов с длиной строки более 60 символов
        count = countGenes60(g);
        System.out.println("Количество генов, с длиной строки более 60 символов: " + count);
        
        //сбрасываем счетчик
        count = 0;
        //считаем количество генов, где C и G превышает коэффициент 0.35 к общему гену
        count = countGenes035(g);
        System.out.println("Количество генов, в котором отношение C и G к остальным символам превышает 0.35: " + count);
        
        //сбрасываем счетчик
        count = 0;
        count = countCTG(g);
        System.out.println("Количество повторений кодона CTG в общей цепи ДНК: " + count);
        
        //поиск длины самого длинного гена
        double longestSoFar = 0.0;
        String longestGen = null;
        if (longestGen == null)
        {
        longestGen = gene;
        longestSoFar = gene.length();
       }
        if (gene.length() > longestSoFar)
        {
        longestGen = gene;
        longestSoFar = gene.length();
        }
        System.out.println("Длина самого длинного гена: " + longestSoFar);
    }
}
public void testcgRatio(){
    String dna = "ATGCCATAG";
    float result = cgRatio(dna);
    System.out.println(result);
}
public void testCountCTG (){
    int result = countCTG ("ATGCCTGATAG");
    result = countCTG ("ATGCTGATAG");
    System.out.println(result);
}
public void testOn(String dna){
    StorageResource genes = getAllGenes(dna);
    for (String g: genes.data()) {
        System.out.println(g);
}
}
public StorageResource Library(){
    StorageResource sr = new StorageResource();
        sr.add("ATGTAA");
        sr.add("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
        sr.add("ATGTTTTTTTTTTTTTTTTAA");
        for(String s : sr.data()){
            System.out.println("my list of genes: " + s);
        }
        return sr;
    }
    public StorageResource LibraryInFile(){
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = fr.asString();
        StorageResource sr = new StorageResource();
                        //dna.toUpperCase();
                        sr.add(dna);
        return sr;
}
public void testProcessGenes(){
    processGenes(Library());
}
public void testProcessGenesInFile(){
    processGenes2(LibraryInFile());
}
public void testFindGene(){
    String dna = "qqqwwwqqqwwwqqqwwwqqq";
    //String gen = findGene(dna);
   // System.out.println(gen);
    
    dna = "qqqATGwwwqqqwwwqqqTAAwwwqqq";
    //gen = findGene(dna);
    //System.out.println(gen);
    
    dna = "qqqATGwwwqqqTAGwwwqqqTAAwwwTGAqqq";
    //gen = findGene(dna);
    //System.out.println(gen);
    
    dna = "qqqATGwwwqqqwwwqqqwwwqqq";
   // gen = findGene(dna);
   // System.out.println(gen);
}
}
