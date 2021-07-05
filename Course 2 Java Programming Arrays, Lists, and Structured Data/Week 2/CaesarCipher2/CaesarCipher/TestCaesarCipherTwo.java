
/**
 * Write a description of class TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
 public String halfOfString (String message, int start){
        String result = new String();
        for (int i=start; i<message.length(); i = i+2){
            result = result + message.charAt(i);
        }
        return result;
    }
private int[] CountLetters (String input){
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
        for (int i=0; i<vals.length; i++){
         if (vals[i] > vals [maxDex]){
             maxDex = i;
            }
        }
        return maxDex;
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
    public void simpleTests (){
    FileResource fr = new FileResource();
    String str = fr.asString();
    //String str = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
    CaesarCipherTwo cct = new CaesarCipherTwo (14,24);
    String encrypted = cct.encrypt(str);
    System.out.println("Encrypted string: " + encrypted);
    breakCaesarCipher (str);
    System.out.println("Decrypted string: " + cct.decrypt(str));
    }
    public void breakCaesarCipher (String input){
    String fhalf = halfOfString(input,0);
    String shalf = halfOfString(input,1);
    int fdkey = getKey(fhalf);
    int sdkey = getKey(shalf);
    System.out.println("Key1 for decrypt a massage: " + fdkey + ". Key2: " + sdkey);
    CaesarCipherTwo cct = new CaesarCipherTwo (fdkey, sdkey);
    System.out.println("Decrypted message: " + cct.decrypt(input));
    }
}
