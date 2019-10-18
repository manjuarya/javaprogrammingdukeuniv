package FirstCSVExample;


/**
 * Write a description of hottestHourInManyDays here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class hottestHourInManyDays {
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
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestTempInFiles = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource (f);
            CSVRecord currentFile = hottestHourInFile(fr.getCSVParser());
            if (largestTempInFiles == null) {
                largestTempInFiles = currentFile;
            }
            else {
                double currentTemp = Double.parseDouble( currentFile.get("TemperatureF"));
                double largestTemp = Double.parseDouble( largestTempInFiles.get("TemperatureF"));
                if ( currentTemp > largestTemp) {
                    largestTempInFiles = currentFile;
                }
            }
        }
        return largestTempInFiles;
    }
    
    public void testHottestTempInManyDays() {
        CSVRecord largestTempInFiles = hottestInManyDays();        
        System.out.println ("Hottest temperature was " + 
                             largestTempInFiles.get("TemperatureF") + 
                             " at " + largestTempInFiles.get("TimeEST")+ 
                             " on " + largestTempInFiles.get("DateUTC") );
    }
    
    
    //public void testHottestInDay () {
        //FileResource fr = new FileResource ("C:/Users/Avinash Arya/Desktop/Manju New Job/Java/CSV/TemperatureData/2015/weather-2015-02-01.csv");
        //CSVRecord largest = hottestInManyDays(fr.getCSVParser());
        //System.out.println ("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC") );
    //}
    
}
