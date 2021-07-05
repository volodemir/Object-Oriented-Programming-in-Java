
/**
 * Write a description of class CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
private ArrayList<String> name;
private ArrayList<Integer> count;

public CharactersInPlay(){
   name = new ArrayList<String>();
   count = new ArrayList<Integer>();
}
public void update (String person){
    person = person.toLowerCase();
    int idx = name.indexOf(person);
    if(idx == -1) {
        name.add(person);
        count.add(1);
    }
    else { 
        int freq = count.get(idx);
        count.set(idx, freq+1);
    }
}
public void findAllCharacters (){
    name.clear();
    count.clear();
    FileResource fr= new FileResource();
    for (String s : fr.lines()){
        int idx = s.indexOf(".");
        if (idx != -1){
            String possibleName = s.substring(0, idx);
            update (possibleName);
        }
        
    }
}
public void charactersWithNumParts (int num1, int num2){
    for (int k=0;k<name.size();k++){
       if ((count.get(k) >= num1) && (count.get(k)<=num2)){
        System.out.println(name.get(k) + " " + count.get(k));
    } 
    }
    
}
public void tester (){
    findAllCharacters();
  for (int i=0;i<name.size();i++){
        if (count.get(i)>2){
    System.out.println("Possible main character: " + name.get(i) + " the number of speaking parts: " + count.get(i));
}
}
charactersWithNumParts (10,15);
}
}

