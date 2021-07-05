
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;


public class Part4 {

    public void webPage() { 
            URLResource page = new URLResource ("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
            
            for (String word : page.words()) {
                String loWord = word.toLowerCase();
                
                if (loWord.contains ("youtube.com")){
                    int startSymbol = loWord.indexOf("\"");
                    int endSymbol = loWord.indexOf("\"");
                    
                    String totalLink = word.substring (startSymbol+1, endSymbol);
                    
                    System.out.println(totalLink);
}
}
}
}

