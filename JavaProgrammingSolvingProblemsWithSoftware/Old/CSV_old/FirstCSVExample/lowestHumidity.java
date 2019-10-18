package FirstCSVExample;
  

/**
 * Write a description of lowestHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class lowestHumidity {
    public CSVRecord getLowestOfTwo(CSVRecord curr, CSVRecord lowHum) {
        if (lowHum == null) {
            lowHum = curr;
        }
        else {
            int ValuelowHum = Integer.parseInt( lowHum.get("Humidity"));
            if (!curr.get("Humidity").equals("N/A")) { 
                System.out.println("The value of current humidity is  " + curr.get("Humidity"));
                int Valuecurr = Integer.parseInt( curr.get("Humidity"));
                if (Valuecurr < ValuelowHum) {
                    lowHum = curr;
                }
            }
        }
        return lowHum;
    }
    
    public CSVRecord lowestHumidityInManyDays () {
        CSVRecord lowestHum = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currFileHum = lowestHumidityInFile(fr.getCSVParser());
            lowestHum = getLowestOfTwo(currFileHum, lowestHum);
            System.out.println("The lowest humidity was  " + currFileHum.get("Humidity") + "   of file  " + f.getName() +" at  " +currFileHum.get("DateUTC"));
        }
        return lowestHum;
    }
            
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currRow : parser) {
            lowestSoFar = getLowestOfTwo(currRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    
    public void testlowestHumidity () {
        CSVRecord lowestHumidity = lowestHumidityInManyDays();
        System.out.println("The lowest humidity of all files was " + lowestHumidity.get("Humidity") + " at  " +
                            lowestHumidity.get("TimeEST") + "  " +lowestHumidity.get("DateUTC")
                            );
    } 
}
