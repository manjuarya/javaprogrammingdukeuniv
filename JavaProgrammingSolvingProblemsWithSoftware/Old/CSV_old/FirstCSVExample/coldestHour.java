package FirstCSVExample;


/**
 * Write a description of coldestHour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 *
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class coldestHour {
    public CSVRecord getColdestOfTwo( CSVRecord currentRow, CSVRecord coldestSoFar ) {
        if ( coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble( currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble( coldestSoFar.get("TemperatureF"));
                if ( currentTemp < coldestTemp && currentTemp >= -274) {
                    coldestSoFar = currentRow;                   
                } 
        }
        return coldestSoFar;
    }
    
    
    public CSVRecord coldestInManyDays() {
        CSVRecord coldestTempInFiles = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource (f);
            CSVRecord currentFile = coldestHourInFile(fr.getCSVParser());
            coldestTempInFiles = getColdestOfTwo( currentFile, coldestTempInFiles );
            System.out.println( "The coldest temp of the file  " + f.getName() + "  is  " + currentFile.get("TemperatureF"));
        }
        return coldestTempInFiles;
    }
        
       
    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for ( CSVRecord currentRow : parser) {
            lowestSoFar = getColdestOfTwo( currentRow, lowestSoFar );
        }
        return lowestSoFar;
    }
    
    
    public void testColdestTempInManyDays() {
        CSVRecord coldestTempInFiles = coldestInManyDays();      
        
        System.out.println ("coldest temperature was " + 
                             coldestTempInFiles.get("TemperatureF") + 
                             //" at " + coldestTempInFiles.get("TimeEDT")+ 
                             " on " + coldestTempInFiles.get("DateUTC") 
                             );
    }
}

