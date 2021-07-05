
/**
 * Write a description of class TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
public int[] CountLetters (String input){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
    public void simpleTests () {
    FileResource fr = new FileResource();
    String str = fr.asString();
    CaesarCipher cc = new CaesarCipher (18);
    String encrypted = cc.encrypt(str);
    System.out.println("Encrypted string: " + encrypted);
    breakCaesarCipher(encrypted);
    System.out.println("Decrypted string: " + cc.decrypt(str));
    
    }
    public void breakCaesarCipher (String input){
    int[] count = CountLetters(input);
    int maxDex = maxIndex(count);
    int dkey = maxDex - 4;
    if (maxDex < 4){
        dkey = 26 - (4 - maxDex);
    }
    System.out.println("Key for decrypt a massage: " + dkey);
    CaesarCipher cc = new CaesarCipher (dkey);
}
    }
