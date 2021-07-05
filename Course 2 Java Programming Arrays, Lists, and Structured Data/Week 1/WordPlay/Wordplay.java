
/**
 * Write a description of class Wordplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Wordplay {
    public boolean isVowel (char ch){
        ch = Character.toLowerCase(ch);
    if (ch == 'a' | ch == 'e' | ch == 'i' | ch == 'o' | ch == 'u')
    return true; 
    else return false;
    }
    public void testIsVowel (){
    char ch = 'a';
    char ch2 = 'F';
    isVowel(ch);
    isVowel(ch2);
    }
    public String replaceVowels (String phrase, char ch){
        StringBuilder sv = new StringBuilder(phrase);
        for (int i=0; i < sv.length(); i++){
            char currChar = sv.charAt(i);
            if (isVowel(currChar)){
                sv.setCharAt(i, ch);
                }
       }        return sv.toString();
        
    }
    public void testReplaceVowels(){
        String str = "Hello World";
        char ch = '*';
        System.out.println(replaceVowels(str, ch));
        }
    public String emphasize (String phrase, char ch){
        StringBuilder sv = new StringBuilder(phrase);
        for (int i=0; i < sv.length(); i++){
            char currChar = sv.charAt(i);
            char lCurrChar= Character.toLowerCase(currChar);
            if(currChar == ch | lCurrChar == ch){
            if (i % 2 == 0){
                sv.setCharAt(i, '*');}
        else sv.setCharAt(i, '+');
    }
}
    return sv.toString();
}
public void testEmphasize (){
    System.out.println(emphasize ("dna ctgaaactga", 'a'));
    System.out.println(emphasize ("Mary Bella Abracadabra", 'a'));
}
}
