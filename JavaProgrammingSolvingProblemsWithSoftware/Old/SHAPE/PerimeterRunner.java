import edu.duke.*;

public class PerimeterRunner {
    
    public int getLargestX (Shape s) {
        int valueX = 0;
        for (Point currPt : s.getPoints()) {
            int currX = currPt.getX();
            if (valueX==0) {
                valueX = currX;
            }
            else {
                if (valueX < currX) {
                    valueX = currX;
                }
            }
        }
        return valueX;        
    }
    
    public double getLargestSide (Shape s) {
        double largestDist = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (largestDist == 0) {
                largestDist = currDist;
            }
            else {
                if (largestDist < currDist) {
                    largestDist = currDist;
                }
            }
            
        }
        return largestDist;        
    }
    
    public double getPerimeter (Shape s) {
        double totalPerim = 0;
        int count = 0;
        double avgLength = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            System.out.println("The distance between points  " + prevPt + " & " + currPt + "  is  " + currDist);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
            count+=1;
        }
        System.out.println("There are total points in this shape is  " + count);
        System.out.println("The average length of all sides of this shape is  " + (totalPerim/count));
        return totalPerim;        
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("The perimeter of this shape is = " + length);
        double largestSide = getLargestSide(s);
        System.out.println("The largest side of this shape is =  " + largestSide);
        int LgtX = getLargestX (s);
        System.out.println("The largest X Value of this shape is =  " + LgtX);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
