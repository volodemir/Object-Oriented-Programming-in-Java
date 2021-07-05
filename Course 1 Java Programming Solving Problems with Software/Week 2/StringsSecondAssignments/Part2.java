
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany (String stringa, String stringb){
        int found = stringb.indexOf(stringa);
        int count = 0;
        while (found != -1){
            count += 1;
            found = stringb.indexOf (stringa, found + 1);
}
return count;
}
public void testHowMany (){
    String stringa = "ATG";
    String stringb = "ATGTAATAGATG";
    int count = howMany(stringa,stringb);
    System.out.println ("Количество повторений " + "'" + stringa + "'" + " в " + "'" + stringb + "'" + " - " + count);
}
}
