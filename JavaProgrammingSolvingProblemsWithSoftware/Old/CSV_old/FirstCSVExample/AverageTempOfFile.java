package FirstCSVExample;


/**
 * Write a description of AverageTempOfFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AverageTempOfFile {
    public double AvgTempInFile (CSVParser parser) {
        double Total = 0;
        int Count = 0;
        for ( CSVRecord currentRow : parser) {
            Total = Total + Double.parseDouble( currentRow.get("TemperatureF"));
            Count = Count +1;
        }
        double AvgTemp = Total/Count;
        return AvgTemp;
    }
    
    public void testAvgTempInDay () {
        FileResource fr = new FileResource ();
        double AverageTemp = AvgTempInFile(fr.getCSVParser());
        System.out.println ("The average temperature is  " + AverageTemp);
    }
}