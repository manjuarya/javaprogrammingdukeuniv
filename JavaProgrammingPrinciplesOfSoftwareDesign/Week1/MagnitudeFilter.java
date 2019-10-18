
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double magMin; 
    private double magMax; 
        
    public MagnitudeFilter(double min, double max) { 
        magMin = min;
        magMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        if (qe.getMagnitude() <= magMax && qe.getMagnitude() >= magMin) {
            return true;
        } 
        return false;
    }
    
    public String getName() {
        String nameOfTheClass = "MagnitudeFilter";
        return nameOfTheClass;
    }
}
