
/**
 * Write a description of averageTemperatureWithHighHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class averageTemperatureWithHighHumidity {
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int count = 0;
        double totalTemp = 0;
        for (CSVRecord currRow : parser) {
            int currhum = Integer.parseInt(currRow.get("Humidity"));
            double currtemp = Double.parseDouble(currRow.get("TemperatureF"));
            if (currhum >= value) {
                totalTemp += currtemp;
                count = count + 1;
                //System.out.println("The current temperature is  " + currtemp + "  at the humidity  " + currhum ); 
              }
        }
        double AvgTemp = totalTemp / count;
        return AvgTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile () {
        int humidity = 80;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double AverageTemp = averageTemperatureWithHighHumidityInFile(parser, humidity);
        System.out.println("The average temperaure with highest humidity " + humidity + " is " + AverageTemp ); 
    } 
}
