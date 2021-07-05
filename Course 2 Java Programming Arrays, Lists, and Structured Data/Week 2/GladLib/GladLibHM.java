
/**
 * Write a description of class GladLibHM here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibHM {
    private HashMap <String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> words;
    private int wordCount = 0;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
public GladLibHM(){
    myMap = new HashMap <String, ArrayList<String>>();
    words = new ArrayList<String>();
    initializeFromSource(dataSourceDirectory);
    myRandom = new Random();
    usedList = new ArrayList<String>();
}
public GladLibHM(String source){
    myMap = new HashMap <String, ArrayList<String>>();
    words = new ArrayList<String>();
    initializeFromSource(dataSourceDirectory);
    myRandom = new Random();
    usedList = new ArrayList<String>();
}

 private void initializeFromSource(String source) {
     String [] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"}; 
     for (String s : categories){
         words = readIt(source + "/" + s +".txt");
         myMap.put(s, words);
        }
    }
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
     private String getSubstitute(String label) {
         if (myMap.containsKey(label)){
             return randomFrom(myMap.get(label));
            }
             if (label.equals("number")){
                 return ""+myRandom.nextInt(50)+5;
                }
         if(!usedList.contains(label)){
         usedList.add(label);
        }
        return "**UNKNOWN**";
    }
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(true){
        if (!usedList.contains(sub)){
            usedList.add(sub);
            wordCount +=1;
            break;
          }
         sub = getSubstitute(w.substring(first+1,last));
}
        return prefix+sub+suffix;
}
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap (){
        int count = 0;
        for (String s : myMap.keySet()){
             count += myMap.get(s).size();
        }
       return count;
    }
    public int totalWordsConsidered (){
        int count = 0;
        for (String key : myMap.keySet()){
          if (usedList.contains(key)){
              count += myMap.get(key).size();
          }
}
return count;
}
    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n"+"the total number of words that were replaced: " + wordCount);
        int totalWords = totalWordsInMap();
        System.out.println("\n"+  "the total number of words: " + totalWords);
        int totalWordsCons = totalWordsConsidered();
        System.out.println("\n"+  "the total number of words in the list of the categories: " + totalWordsCons);
        usedList.clear();
    }
}
