
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part2 {
    
    public int howMany(String stringa, String stringb) {        
        int indexStringA = stringb.indexOf(stringa);
        int count = 0;
        while (indexStringA != -1) {
            count += 1;
            int lengthStringA = stringa.length();
            stringb = stringb.substring(indexStringA + lengthStringA);
            indexStringA = stringb.indexOf(stringa);
        }
        return count;
    } 
    
    public void testHowMany() {
        String A = "GAA";
        String B = "ATGAACGAATTGAATC";
        System.out.println("strina is: " + A + "  & stringb is:  " + B);
        int count = howMany(A, B);
        System.out.println("The stringa persist  " + count + "  times in stringb.");
        A = "AA";
        B = "ATAAAA";
        System.out.println("strina is: " + A + "  & stringb is:  " + B);
        count = howMany(A, B);
        System.out.println("The stringa persist  " + count + "  times in stringb.");
    }
}
