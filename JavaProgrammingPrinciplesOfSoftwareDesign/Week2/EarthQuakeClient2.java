
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
        
        Filter f1 = new MagnitudeFilter(3.5, 4.5);
        Filter f2 = new DepthFilter(-55000.0, -20000.0); 
        //magnitude between 4.0 and 5.0 inclusive and depth between -35,000.0 and -12,000.0 inclusive

        //that are 1,000,000 meters (1,000 km) from Denver, Colorado whose location is (39.7392, -104.9903), and that end with an ‘a’ 
        
        /*Location Denver = new Location(39.7392, -104.9903);
        String requset = "end";
        String word = "a";
        Filter f1 = new DistanceFilter(Denver, 1000000);
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

        //magnitude between 1.0 and 4.0 inclusive, to test the depth between -180,000.0 and -30,000.0 inclusive, and if the letter “o” 
        //is in the title 
        
        String requset = "any";
        String word = "o";
        
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0));
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
        
        //0.0 and 5.0 inclusive, to test for the distance from Billund, Denmark at location (55.7308, 9.1153) is less 
        //than 3,000,000 meters (3000 km), and if the letter “e” is in the title

        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        
        Location Billund = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(Billund, 3000000));
        
        String requset = "any";
        String word = "e";
        maf.addFilter(new PhraseFilter(requset, word));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        }
        System.out.println("The total records found: " + quakes.size());
        System.out.println("The name of the filters are : " + maf.getName());
    }
}
