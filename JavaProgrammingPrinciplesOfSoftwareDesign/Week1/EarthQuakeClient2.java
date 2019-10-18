
/**
 * Write a description of EarthQuakeClient2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe);
            } 
        } 
        
        return answer;
    }
    
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f1, Filter f2) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f1.satisfies(qe)) { 
                if (f2.satisfies(qe)) {
                    answer.add(qe);
                }
            } 
        } 
        
        return answer;
    }

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        //Filter f = new MinMagFilter(4.0); 
        
        Filter f1 = new MagnitudeFilter(4.0, 5.0);
        Filter f2 = new DepthFilter(-35000.0, -12000.0); 
        //magnitude between 4.0 and 5.0 inclusive and depth between -35,000.0 and -12,000.0 inclusive
        //less than 10,000,000 meters (10,000 km) from Tokyo, Japan whose location is (35.42, 139.43), 
        //and that are in Japan (this means “Japan” is the last word 

        /*Location Tokyo = new Location(35.42, 139.43);
        String requset = "end";
        String word = "Japan";
        Filter f1 = new DistanceFilter(Tokyo, 10000000);
        Filter f2 = new PhraseFilter(requset, word);*/
        
        ArrayList<QuakeEntry> quakes  = filter(list, f1, f2); 
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 
        System.out.println("The total quakes are: " + quakes.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
        
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        MatchAllFilter maf = new MatchAllFilter();
        //magnitude between 0.0 and 2.0, to test the depth between -100000.0 and -10000.0, and if the letter “a” is in the title
                
        String requset = "any";
        String word = "a";
        
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-100000.0, -10000.0));
        maf.addFilter(new PhraseFilter(requset, word));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 
        System.out.println("The total records found: " + quakes.size());
        System.out.println("The name of the filters are : " + maf.getName());
    } 
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());        
        MatchAllFilter maf = new MatchAllFilter();
        //magnitude between 0.0 and 3.0, to test for the distance from Tulsa, Oklahoma at location (36.1314, -95.9372) 
        //is less than 10000000 meters (10000 km), and if the substring “Ca” is in the title

        maf.addFilter(new MagnitudeFilter(0.0, 3.0));
        
        Location Tulsa = new Location(36.1314, -95.9372);
        maf.addFilter(new DistanceFilter(Tulsa, 10000000));
        
        String requset = "any";
        String word = "Ca";
        maf.addFilter(new PhraseFilter(requset, word));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        }
        System.out.println("The total records found: " + quakes.size());
        System.out.println("The name of the filters are : " + maf.getName());
    }
}
