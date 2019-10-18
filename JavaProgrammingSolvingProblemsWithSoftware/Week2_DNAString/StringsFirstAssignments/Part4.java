
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part4 {
    
    
    
    public void weblink() {
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String x = "youtube";
        for (String word : ur.words()) {
            String wordLower = word.toLowerCase();
            int foundIndex = wordLower.indexOf(x);
            if (foundIndex != -1) {
                int startIndex = word.indexOf("\"");
                int stopIndex = word.lastIndexOf("\"");
                String result = word.substring(startIndex+1, stopIndex);
                System.out.println(result);
            }
        }
    }
}
