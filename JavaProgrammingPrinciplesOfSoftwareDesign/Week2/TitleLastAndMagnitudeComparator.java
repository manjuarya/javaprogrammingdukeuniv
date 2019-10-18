/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String title1 = getLastWord(q1.getInfo());
        String title2 = getLastWord(q2.getInfo());
        
        if (title1.compareTo(title2) < 0) {
            return -1;
        }
        if (title1.compareTo(title2) > 0) {
            return 1;
        }
        if (title1.compareTo(title2) == 0) {
            if (q1.getMagnitude() < q2.getMagnitude()) {
                return -1;
            }
            if (q1.getMagnitude() > q2.getMagnitude()) {
                return 1;
            }
            return 0;
        }
        
        // never reached
        return 0;
    }
    
    public String getLastWord(String title) {
        int index = title.lastIndexOf(" ");
        String titleLastWord = title.substring(index+1);
        return titleLastWord;
    }
        
}
