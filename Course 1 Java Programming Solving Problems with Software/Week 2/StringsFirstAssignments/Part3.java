
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
 public boolean twoOccurrences (String stringa, String stringb){
     int found = stringb.indexOf(stringa);
     int count = 0;
     
     while (found != -1){
         count += 1;
         found = stringb.indexOf(stringa, found+1);
}
if (count >= 2)
{
return true;
}
else {
    return false;
}

}
public String lastPart(String stringa, String stringb){
    if (stringb.indexOf(stringa) != -1) {
        int startOfString = stringb.indexOf(stringa);
        int endOfString = stringb.lastIndexOf (stringa);
        
        return stringb.substring (startOfString + (endOfString - 1), stringb.length());
}
return stringb;
}
public void testing(){
String stringa = "про";
String stringb = "История про профессионала";
boolean total = twoOccurrences(stringa, stringb);
System.out.println ("Total is: " + total);

stringa = "про";
stringb = "История без профессионала";
total = twoOccurrences(stringa, stringb);
System.out.println ("Total is: " + total);

stringa = "про";
stringb = "История без мастера";
total = twoOccurrences(stringa, stringb);
System.out.println ("Total is: " + total);

stringa = "an";
stringb = "banana";
String total1 = lastPart(stringa, stringb);
System.out.println ("Total is: " + total1);

stringa = "зоопарк";
stringb = "лес";
total1 = lastPart(stringa, stringb);
System.out.println ("Total is: " + total1);
}
}