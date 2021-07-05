
/**
 * Write a description of class CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo{
private String alphabet;
private String shiftedAlphabet1;
private String shiftedAlphabet2;
private int mykey1;
private int mykey2;

public CaesarCipherTwo (int key1, int key2){
    alphabet = "abcdefghijklmnopqrstuvwxyz";
    shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
    shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
    mykey1 = key1;
    mykey2 = key2;
}
public String encrypt (String input){
    StringBuilder sb = new StringBuilder(input);
    for (int i=0; i<sb.length();i++){
        char currChar = Character.toLowerCase(sb.charAt(i));
        int idx = alphabet.indexOf(currChar);
        if (idx != -1){
            if (i%2 == 0){
            char newChar = shiftedAlphabet1.charAt(idx);
            sb.setCharAt(i, newChar);
        }
        else {
            char newChar = shiftedAlphabet2.charAt(idx);
            sb.setCharAt(i, newChar);
        }
    }
    }
    return sb.toString();
}
public String decrypt (String input){
    CaesarCipherTwo cc = new CaesarCipherTwo(26 - mykey1, 26 - mykey2);
    return cc.encrypt(input);
}
}
