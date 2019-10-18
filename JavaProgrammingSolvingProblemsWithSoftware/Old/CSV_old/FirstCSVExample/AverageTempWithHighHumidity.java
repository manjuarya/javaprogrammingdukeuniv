package FirstCSVExample;


/**
 * Write a description of AverageTempWithHighHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AverageTempWithHighHumidity {
    
    public double highestHumidityInFile() {
        int count = 0;
        double total = 0;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord currRow : parser) {
            int currhum = Integer.parseInt(currRow.get("Humidity"));
            double currtemp = Double.parseDouble(currRow.get("TemperatureF"));
            if (currhum >= 80) {
                total = currtemp + total;
                count = count + 1;
                System.out.println("The current temperature is  " + currtemp + "  at the humidity  " + currhum ); 
              }
        }
        double AvgTemp = total / count;
        return AvgTemp;
    }

    public void testAvgTempWithHighHum () {
        double AverageTemp = highestHumidityInFile();
        System.out.println("The average temperaure with highest humidity (80) is   " + AverageTemp ); 
    } 
    
}
