
/**
 * Write a description of class WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
public WordsInFiles (){
 map = new HashMap<String, ArrayList<String>>(); 
}
private void addWordsFromFile (File f){
    FileResource fr = new FileResource(f);
    for (String w : fr.words()){
     
     if (map.containsKey(w)){
     ArrayList<String> words = map.get(w);
     if (!words.contains(f.getName())){
         words.add(f.getName());
        }
    }
    else {
       ArrayList<String> words = new ArrayList<String>();
       words.add (f.getName()); 
       map.put(w, words);
    }
}
}
private void buildWordFileMap(){
 map.clear();
 DirectoryResource dr = new DirectoryResource();
 for (File f : dr.selectedFiles()){
     addWordsFromFile(f);
    }
}
private int maxNumber(){
    int indexOfMax = 0;
    for (String s : map.keySet()){
    int occurr = map.get(s).size();
    if (occurr > indexOfMax){
    indexOfMax = occurr;
}
}
return indexOfMax;
}
private ArrayList<String> wordsInNumFiles (int number){
    ArrayList<String> words = new ArrayList<String>();
    for (String s : map.keySet()){
        if (map.get(s).size() == number){
            words.add(s);
        }
    }
    return words;
}
private void printFilesIn (String word){
    System.out.println("Files contains " + word);
    for (String filename : map.get(word)){
    System.out.println(filename);
}
}
public void tester(){
    buildWordFileMap();
    int max = maxNumber();
    System.out.println("count words: " + map.size());
    System.out.println("Max count of words in files " + max);
    ArrayList<String> winf = wordsInNumFiles(4);
    System.out.println("Max count of words in 4 files " + winf.size());
    printFilesIn ("tree");
    printFilesIn ("laid");
    ArrayList<String> words = wordsInNumFiles(max);
    for (int i=0;i<words.size();i++){
     String word = words.get(i);   
    }
    System.out.println("Count of words that appear in " + max + " files: " + words.size());
}
}


