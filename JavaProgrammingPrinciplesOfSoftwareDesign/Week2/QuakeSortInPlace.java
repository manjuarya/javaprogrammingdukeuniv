
/**
 * Write a description of QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }        
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int count = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            count = i+1;
            if (checkInSortedOrder(in)) {
                break;
            }            
        }
        
        System.out.println("Total " + count + "  passes were needed to sort the elements");
    } 
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    } 
    
    public void sortByDepth(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< 70; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }        
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        
        for (int k=1; k<=numSorted; k++) {
            //System.out.println("Printing Quakes after pass " + k);
            for (int i=0; i<quakeData.size()-k; i++) {
                if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                    QuakeEntry magI = quakeData.get(i+1);
                    QuakeEntry magI1 = quakeData.get(i);
                    quakeData.set(i, magI);
                    quakeData.set(i+1, magI1);
                }
            }
            /*for (QuakeEntry qe: quakeData) { 
                System.out.println(qe);
            }*/  
            if (checkInSortedOrder(quakeData)) {
                System.out.println("Total " + k + "  passes were needed to sort the elements");
                break;
            }
        } 
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        //System.out.println("The data sorted after " + N + " pass");
        onePassBubbleSort(in, in.size()-1);
        
    } 
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int count = 0;
        //for (int j=1; j<in.size(); j++) {
        onePassBubbleSort(in, in.size()-1);
            /*if (checkInSortedOrder(in)) {
                System.out.println("Total " + j + "  passes were needed to sort the elements");
                count = j;
                break;
            }*/ 
        

        //System.out.println("Total " + count + "  passes were needed to sort the elements");
    } 
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=0; i<quakes.size()-1; i++) {
            if (!(quakes.get(i).getMagnitude() <= quakes.get(i+1).getMagnitude())) {
                return false;
            }            
        }
        return true;
    } 
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom.txt";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }*/        
        
        //sortByMagnitude(list);
        //sortByDepth (list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        System.out.println("After sorting");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }        
    }    
}
