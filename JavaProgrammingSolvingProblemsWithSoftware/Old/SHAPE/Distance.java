
/**
 * Write a description of Distance here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;


public class Distance {
   

    public double getdistance ( Shape s) {
        double length = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            length = prevPt.distance(currPt);
            System.out.println("The distance between points  " + prevPt + "  " + currPt + "  is  " + length);
            prevPt = currPt;
        }
        return length;
    }
    
    
    public void testDist() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getdistance(s);
        System.out.println("perimeter = " + length);
    }
}
