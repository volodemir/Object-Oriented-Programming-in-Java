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
    public String breakForLanguage (String encrypted, HashSet<String> dictionary){
        String decrypted = "";
        int max = 0;
        int keyLength = 0;
        int[] key = new int[0];
        int count = 0;
        for (int i=1; i<=100; i++){
        int[] possibleKey = tryKeyLength (encrypted, i, 'e');
        VigenereCipher vc = new VigenereCipher(possibleKey);
        String tempDecr = vc.decrypt(encrypted);
        count = countWords(tempDecr,dictionary);
        if (count > max){
            max = count;
            decrypted = tempDecr;
            keyLength = i;
            key = tryKeyLength (encrypted, keyLength, 'e');
            VigenereCipher vc2 = new VigenereCipher(key);
        }
        
    }
    System.out.println("decrypted message: " + decrypted);
    System.out.println("key length is: " + keyLength);
    for (int i = 0; i<keyLength; i++){
    System.out.println("key " + (i+1) + " used: " + key[i]);
}
    System.out.println("This file contains " + max + " valid words");
    
    return decrypted;
    }
    /*public void testReadDic(){
        FileResource fr = new FileResource();
        System.out.println(readDictionary(fr));
    }*/
    /*public void testCountWords(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        System.out.println("count of words: " + countWords("i have Alma and need", dictionary));
        
    }*/
    public void breakVigenere () {
       /* //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String s = fr.asString();
        int[] key = tryKeyLength(s, 4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println("decrypted message: " + vc.decrypt(s));
        for (int i=0; i<key.length;i++){
        System.out.println("key used: " + key[i]);
    }*/
        FileResource fr = new FileResource();
        String s = fr.asString();
        FileResource fr2 = new FileResource("dictionaries/English");
        HashSet<String> rd = readDictionary(fr2);
        breakForLanguage(s, rd);
    }
    
    public void testSliceString(){
     System.out.println(sliceString("abcdefghijklm", 0, 3));
     System.out.println(sliceString("abcdefghijklm", 0, 4));
     System.out.println(sliceString("abcdefghijklm", 2, 5));
    }
}
