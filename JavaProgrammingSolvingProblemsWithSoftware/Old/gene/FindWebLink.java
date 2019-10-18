
/**
 * Write a description of FindWebLink here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class FindWebLink {
    public void testWebLink() {
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html"); 
        for (String s : url.lines()) {
            String searchQuote = "\"";
            String searchText = "youtube";
            String lowerCase = s.toLowerCase();
            int foundYouTube = lowerCase.indexOf(searchText);
            
            if(foundYouTube >= 0)
            {
                int startQuote = lowerCase.indexOf(searchQuote);
                int stopQuote = lowerCase.lastIndexOf(searchQuote);
                String youtube = s.substring(startQuote, stopQuote);            
                System.out.println("The Youtube URL is: " + youtube);
            }
            
        }
    }
}
