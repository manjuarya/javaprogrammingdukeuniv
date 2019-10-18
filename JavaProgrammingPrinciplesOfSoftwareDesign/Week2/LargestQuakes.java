
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class LargestQuakes {
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        if (quakeData.size() < howMany) {
            return quakeData;
        }
        for(int j=0; j < howMany; j++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }
    
    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        double maxMag = 0;
        int maxIndex = 0;
        for(int k=0; k < data.size(); k++){
                QuakeEntry quake = data.get(k);
                double mag = quake.getMagnitude();
                if (maxMag < mag){
                    maxMag = mag; 
                    maxIndex = k;
                }
        }
        return maxIndex;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        /*for (QuakeEntry qe : list) {
            System.out.println("The QuakeEntry data is  " + qe);
        }*/
        System.out.println("read data for " + list.size() + " quakes");
        
        /*
        for (QuakeEntry qe : list) {
            if (qe.getMagnitude() > 5.0) {
                System.out.println(qe);
            }
        }
        */
        int maxMagnitudeIndex = indexOfLargest(list);
        System.out.println("The maximum magnitude in this file is  " + list.get(maxMagnitudeIndex).getMagnitude() + 
                            "  at location " + maxMagnitudeIndex);
        ArrayList<QuakeEntry> largestQuakesInFile = getLargest(list, 50);
        for (QuakeEntry qe : largestQuakesInFile) {
            System.out.println(qe);
        }
        System.out.println("The total " + largestQuakesInFile.size() + "  maximum quakes in the file ");
    }
}
