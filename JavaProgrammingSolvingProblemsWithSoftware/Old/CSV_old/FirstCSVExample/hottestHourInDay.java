package FirstCSVExample;


/**
 * Write a description of hottestHourInDay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class hottestHourInDay {
    public CSVRecord hottestHourInFile (CSVParser parser) {
        CSVRecord largestSoFar = null;
        for ( CSVRecord currentRow : parser) {
            if ( largestSoFar == null) {
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble( currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble( largestSoFar.get("TemperatureF"));
                if ( currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                } 
            }
        }
        return largestSoFar;
    }
    
    public void testHottestInDay () {
        FileResource fr = new FileResource ("C:/Users/Avinash Arya/Desktop/Manju New Job/Java/CSV/TemperatureData/2015/weather-2015-02-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println ("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST") );
    }
}