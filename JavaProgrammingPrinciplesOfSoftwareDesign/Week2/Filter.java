
/**
 * Write a description of Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter {
    public  boolean satisfies(QuakeEntry qe); 
    
    public String getName();
}
