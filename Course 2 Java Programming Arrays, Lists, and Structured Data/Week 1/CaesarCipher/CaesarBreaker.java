
/**
 * Write a description of class CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.lang.*;

public class CaesarBreaker {
    public int[] CountLetters (String input){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int [26];
        for (int i=0; i < input.length(); i++){
            char ch = Character.toLowerCase(input.charAt(i));
            int dex = alphabet.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int [] vals){
        int maxDex = 0;
        int indexOfMax = 0;
        for (int i=0; i<vals.length; i++){
         if (vals[i] > maxDex){
             maxDex = vals[i];
             indexOfMax = i;
            }
        }
        return indexOfMax;
    }
    public String decrypt (String encrypted, int dkey){
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - dkey);
        return message;
}
public String decryptTwo (String encrypted, int dkey1, int dkey2){
    CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2);
        return message;
}
    public void testDecrypt (){
        /*FileResource fr = new FileResource ("wordsLotsOfEsEncrypted.txt");
          String encrypt = fr.asString();*/
         System.out.println (decrypt("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!", 23));
}
    public String halfOfString (String message, int start){
        String result = new String();
        for (int i=start; i<message.length(); i = i+2){
            result = result + message.charAt(i);
        }
        return result;
    }
public void testHalfOfString (){
    System.out.println (halfOfString("Qbkm Zgis", 0));
    System.out.println (halfOfString("Qbkm Zgis", 1));
}
public int getKey (String s){
    int[] count = CountLetters(s);
    int maxDex = maxIndex(count);
    int dkey = maxDex - 4;
    if (maxDex < 4){
        dkey = 26 - (4 - maxDex);
    }
    return dkey;
}
public String decryptTwoKeys (String encrypted){
    CaesarCipher cc = new CaesarCipher();
    String fhalf = halfOfString(encrypted,0);
    String shalf = halfOfString(encrypted,1);
    System.out.println (fhalf);
    StringBuilder decrypt = new StringBuilder(encrypted);
    int keyOne = getKey (fhalf);
    int keyTwo = getKey (shalf);
    
    String encrypted1 = cc.encrypt(fhalf,(26-keyOne));
    String encrypted2 = cc.encrypt(shalf,(26-keyTwo));
    
    for (int i=0; i<fhalf.length(); i++){
        decrypt.setCharAt(2*i, encrypted1.charAt(i));
    }
    for (int i=0; i<shalf.length();i++){
     decrypt.setCharAt (2*i+1, encrypted2.charAt(i));
    }
    System.out.println ("Key 1: " + keyOne + " Key 2: " + keyTwo);
    return decrypt.toString();
}
public void testDecryptTwoKeys(){
    /*String message1 = "Top ncmy qkff vi vguv vbg ycpx";
    String message2 = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
    System.out.println("Decrypted message = " + decryptTwoKeys(message1 + " " + message2)); */
    FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
    String str = fr.asString();
    System.out.println("Decrypted message = " + decryptTwoKeys(str));
}
public void testDecryptTwo(){
    String message = "Top ncmy qkff vi vguv vbg ycpx";
    System.out.println("Decrypted message = " + decryptTwo(message, 2, 20));
}
}
