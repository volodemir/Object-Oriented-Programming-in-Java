
/**
 * Write a description of class CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt (String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String tolowAlphabet = alphabet.toLowerCase();
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String shiftedLowAlphabet = shiftedAlphabet.toLowerCase();
        for (int i=0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            int lidx = tolowAlphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);    
            }
            if (lidx != -1){
            char newLChar = shiftedLowAlphabet.charAt(lidx);
                
                encrypted.setCharAt(i, newLChar);
            }
        }
        return encrypted.toString();
}
public String encryptTwoKeys (String input, int key1, int key2){
    String one = encrypt(input, key1);
    String two = encrypt(input, key2);
    StringBuilder oneEncrypted = new StringBuilder(one);
    StringBuilder twoEncrypted = new StringBuilder(two);
    for (int i=0; i<oneEncrypted.length();i++){
        if (i != -1){
      if (i == 0 || i%2 == 0){
            char newChar = one.charAt(i);
            oneEncrypted.setCharAt(i, newChar);
        }
        else {
            char newChar = two.charAt(i);
            oneEncrypted.setCharAt(i, newChar);
        }
    }
    }
    return oneEncrypted.toString();
}
public void testCaesar(){
    int key = 15;
    /*FileResource fr = new FileResource();
    String message = fr.asString();
    String encrypted = encrypt(message, key);
    System.out.println("key is " + key + "\n" + encrypted);*/
    System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
    /*String decrypted = encrypt(encrypted, 26-key);
    System.out.println(decrypted);*/

}
public void testEncryptTwoKeys (){
    //System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    /*FileResource fr = new FileResource("wordsLotsOfEs.txt");
    String str = fr.asString();
    System.out.println(encryptTwoKeys(str, 23, 2));*/
    System.out.println(encryptTwoKeys("First Legion", 23, 17));
}
}