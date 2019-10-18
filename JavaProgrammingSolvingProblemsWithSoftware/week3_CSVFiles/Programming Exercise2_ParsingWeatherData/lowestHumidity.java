
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
    
    private CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    private CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (!currentRow.get("Humidity").equals("N/A")) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            else {
                int ValuelowHum = Integer.parseInt(lowestSoFar.get("Humidity"));
                int Valuecurrent = Integer.parseInt( currentRow.get("Humidity"));
                //System.out.println("The value of current humidity is  " + currentRow.get("Humidity"));                
                if (Valuecurrent < ValuelowHum) {
                    lowestSoFar = currentRow;
                }   
            }
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " +  csv.get("DateUTC")); 
    }
    
    private CSVRecord lowestHumidityInManyFiles () { 
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
    
    public void testLowestHumidityInManyFiles () {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("The lowest humidity of all files was " + lowestHumidity.get("Humidity") + " at  " +
                            lowestHumidity.get("TimeEDT") + "  " +lowestHumidity.get("DateUTC")
                            );
    } 
}
