
/**
 * Write a description of class WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies {
private ArrayList<String> myWords;
private ArrayList<Integer>myFreqs;
private int indexOfMax;

public WordFrequencies(){
   myWords = new ArrayList<String>();
   myFreqs = new ArrayList<Integer>();
   indexOfMax = 0;
}
public void findUnique (){
    myWords.clear();
    myFreqs.clear();
    FileResource fr= new FileResource();
    for (String s : fr.words()){
        s = s.toLowerCase();
        int index = myWords.indexOf(s);
        if (index == -1){
         myWords.add(s);
         myFreqs.add(1);
        }
        else {
         int value = myFreqs.get(index);
         myFreqs.set(index,value+1);
        }
    }
}
public void tester(){
    findUnique();
    System.out.println(" unique words: " + myWords.size());
  /* for (int k=0; k<myWords.size(); k++){
   System.out.println("The number is unique words is " + myFreqs.get(k) + " and frequency of word is " + myWords.get(k));
}*/
System.out.println("The word that occurs most often and its count are: " + myWords.get(indexOfMax) + " " + (findIndexOfMax()));
}
public int findIndexOfMax (){

    int max = 0;
    for (int k=0; k<myFreqs.size(); k ++){
        if (myFreqs.get(k) > max){
            max = myFreqs.get(k);
            indexOfMax = k;
        }
    }
    return max;
}
}
