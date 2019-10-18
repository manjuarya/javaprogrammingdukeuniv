
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location myLocation; 
    private double maxDistance; 
        
    public DistanceFilter(Location city, double distance) { 
        myLocation = city;
        maxDistance = distance;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        if (qe.getLocation().distanceTo(myLocation) < maxDistance) {
            return true;
        } 
        return false;
    }
    
    public String getName() {
        String nameOfTheClass = "DistanceFilter";
        return nameOfTheClass;
    }
}
