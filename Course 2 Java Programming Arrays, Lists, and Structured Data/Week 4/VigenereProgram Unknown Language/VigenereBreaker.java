import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder(message);
        StringBuilder n = new StringBuilder();
        for (int i = whichSlice; i < sb.length(); i += totalSlices){
        char shifted = sb.charAt(i);
        n.append(shifted);
    }
        return n.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for (int i=0; i<klength; i++){
            String n = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(n);
    }
        return key;
    }
    public HashSet<String> readDictionary (FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String s : fr.lines()){
            hs.add(s.toLowerCase());
        }
    return hs;
    }
    public int countWords (String message, HashSet<String> dictionary){
        int count = 0;
        for (String word : message.split("\\W+")) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                count +=1;
            }
        }
        return count;
    }
    public char mostCommonCharIn (HashSet<String> dictionary){
        char mostCC = 'a';
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        int max = 0;
        for (String s : dictionary){
            s = s.toLowerCase();
            for (int i = 0; i < s.length(); i++){
             int index = alphabet.indexOf(s.charAt(i));
             if (index != -1){
                 count[index] +=1; 
                 }
            }
            }
        for (int i=0; i<count.length; i++){
         if (count[i] > count[max]){
             max = i;
            }
        }
        mostCC = alphabet.charAt(max);
        return mostCC;
    }
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
     String decrypted = "";
     int max = 0;
     String language = "";
     int cWords = 0;
        for (String s : languages.keySet()){
         HashSet<String> dictionary = languages.get(s);
         decrypted = breakForLanguage (encrypted, dictionary);
         cWords = countWords(decrypted, dictionary);
         if (cWords > max){
             max = cWords;
             decrypted = breakForLanguage (encrypted, dictionary);
             language = s;
            }
        }
     System.out.println("the decrypted message: " + decrypted);
     System.out.println("identified language is " + language);
    }
    public String breakForLanguage (String encrypted, HashSet<String> dictionary){
        String decrypted = "";
        int max = 0;
        int keyLength = 0;
        int[] key = new int[0];
        int count = 0;
        for (int i=1; i<=100; i++){
        int[] possibleKey = tryKeyLength (encrypted, i, mostCommonCharIn(dictionary));
        VigenereCipher vc = new VigenereCipher(possibleKey);
        String tempDecr = vc.decrypt(encrypted);
        count = countWords(tempDecr,dictionary);
        if (count > max){
            max = count;
            decrypted = tempDecr;
            keyLength = i;
            key = tryKeyLength (encrypted, keyLength, mostCommonCharIn(dictionary));
            VigenereCipher vc2 = new VigenereCipher(key);
        }
        
    }
    /*System.out.println("decrypted message: " + decrypted);*/
    System.out.println("key length is: " + keyLength);
    for (int i = 0; i<keyLength; i++){
    System.out.println("key " + (i+1) + " used: " + key[i]);
}
    //System.out.println("This file contains " + max + " valid words");
    return decrypted;
    }
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        FileResource danish = new FileResource ("dictionaries/Danish");
        FileResource dutch = new FileResource("dictionaries/Dutch");
        FileResource english = new FileResource("dictionaries/English");
        FileResource french = new FileResource("dictionaries/French");
        FileResource german = new FileResource("dictionaries/German");
        FileResource italian = new FileResource("dictionaries/Italian");
        FileResource portuguese = new FileResource("dictionaries/Portuguese");
        FileResource spanish = new FileResource("dictionaries/Spanish");
        languages.put("English", readDictionary(english));
        languages.put("Danish", readDictionary(danish));
        languages.put("Dutch", readDictionary(dutch));
        languages.put("French", readDictionary(french));
        languages.put("German", readDictionary(german));
        languages.put("Italian", readDictionary(italian));
        languages.put("Portuguese", readDictionary(portuguese));
        languages.put("Spanish", readDictionary(spanish));
        System.out.println("mostCommonCharIn " + mostCommonCharIn (readDictionary(english)));
        breakForAllLangs(encrypted, languages);
    }
}
