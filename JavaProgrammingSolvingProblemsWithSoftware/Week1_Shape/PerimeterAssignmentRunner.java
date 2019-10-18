
/**
 * Write a description of PerimeterAssignmentRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class PerimeterAssignmentRunner {
    
    private double getPerimeter(Shape s) {
        Point prevPt = s.getLastPoint();
        double totalPerim = 0;
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }
    
    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        for (Point p : s.getPoints()) {
            System.out.println(p);
        }
        int nosOfPoints = getNumPoints(s);
        System.out.println("There are total  " + nosOfPoints + "  Points in this file");
        double totalPerimeter = getPerimeter(s);
        System.out.println("The total perimeter of this shape is  " + totalPerimeter);
        double avgLength = getAverageLength(s);
        System.out.println("The average length of this shape is  " + avgLength);
        double largestSide = getLargestSide(s);
        System.out.println("The largest side of this shape is  " + largestSide);
        int largestX = getLargestX(s);
        System.out.println("The largest X value of this shape is  " + largestX);
        printFileNames();
    }
    
    private int getNumPoints(Shape s) {
        int totalPoints = 0;
        for (Point p : s.getPoints()) {
            totalPoints = totalPoints + 1;
        }
        return totalPoints;
    }
    
    private double getAverageLength(Shape s) {
        double totalPerimeter = getPerimeter(s);
        int totalPoints = getNumPoints(s);
        double avgLength = totalPerimeter / totalPoints;
        return avgLength;
    }
    
    private double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double largestSide = 0;
        for (Point currPt : s.getPoints()) {            
            double currDist = prevPt.distance(currPt);
            if (largestSide < currDist) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }
    
    private int getLargestX(Shape s) {
        int x = 0;
        for (Point p : s.getPoints()) {
            int currX = p.getX();
            if (x < currX) {
                x = currX;
            }            
        }
        return x;
    }
    
    private void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println("The file name is  " + f.getName());
        }
    } 
    
    private double getLargestPerimeterMultipleFiles() {
        double perimeter = 0;
        double largestPerimeter = 0;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            perimeter = getPerimeter(s);
            if (largestPerimeter < perimeter) {
                largestPerimeter = perimeter;
                fileName = f.getName();
            }
        }
        System.out.println("The file  " + fileName + "  is having the largest perimeter.");
        return largestPerimeter;
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter is  " + largestPerimeter);
    }
    
    public void triangle() {
        
    } 
    
    public static void main() {
        
    }
}
