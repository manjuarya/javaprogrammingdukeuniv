package FirstCSVExample;


/**
 * Write a description of AverageTempOfManyFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AverageTempOfManyFiles {

       
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
    
    public double AvgTempInManyFile () {
        int count = 0;
        double TotalTemp = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            double CurrFileAvgTemp = AvgTempInFile(fr.getCSVParser());
            System.out.println ("The average temperature of the file  " + f.getName() + "  is  " + CurrFileAvgTemp);
            TotalTemp = CurrFileAvgTemp + TotalTemp;
            count = count+1;
        }
        double AvgTemp = TotalTemp/count;
        return AvgTemp;
    }
    
    public void testAvgTempInManyDay () {
        double AverageTemp = AvgTempInManyFile();
        System.out.println ("The average temperature is  " + AverageTemp);
    }
}
