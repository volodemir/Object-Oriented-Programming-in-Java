
/**
 * Write a description of class WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths (FileResource resource, int [] counts){
        int numberOfWords = 0;
        for(String word : resource.words()){
            word = word.toLowerCase();
            int length = word.length();
            if(Character.isLetter(word.charAt(0)) == false || Character.isLetter(word.charAt(word.length()-1)) == false){
                length --;
            }
             counts[length]++;
            numberOfWords ++;
}
         for (int k=0; k<numberOfWords;k++){
             if (counts[k] != 0){
             System.out.println(counts[k] + " words of length " + k);
            }
            }
           System.out.println ("Max Index = " + indexOfMax (counts)); 
    }
    public void testCountWordLengths (){
     FileResource resource = new FileResource ("CommonWordsData/manywords.txt");
     int [] counts = new int [31];
     countWordLengths (resource, counts);
    }
    public int indexOfMax (int [] values){
        int max = 0;
        for (int i=0;i<values.length;i++){
            if (values[i] > max){
                max = values[i];
            }
        }
        return max;
    }
}

