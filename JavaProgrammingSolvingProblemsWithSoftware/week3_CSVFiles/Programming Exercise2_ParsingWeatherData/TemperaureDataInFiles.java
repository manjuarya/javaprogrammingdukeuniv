
/**
 * Write a description of TemperaureDataInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class TemperaureDataInFiles {
    
    private CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow : parser) {
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        } 
        return coldestSoFar;
    }     
    
    private CSVRecord getColdestOfTwo( CSVRecord currentRow, CSVRecord coldestSoFar ) {
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
    
    public void testColdestHourInFile () {
        FileResource fr = new FileResource ();
        CSVRecord coldestTempInADay = coldestHourInFile(fr.getCSVParser());
        
        System.out.println( "The coldest temp at  " + coldestTempInADay.get("TimeEDT") + "  was  " + coldestTempInADay.get("TemperatureF"));
    }    
    
    private CSVRecord coldestHourInManyFile() {
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
        
    private String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource (f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord currentRow : parser) {
                if ( coldestSoFar == null) {
                    coldestSoFar = currentRow;
                }
                else {
                    double currentTemp = Double.parseDouble( currentRow.get("TemperatureF"));
                    double coldestTemp = Double.parseDouble( coldestSoFar.get("TemperatureF"));
                    if ( currentTemp < coldestTemp && currentTemp >= -274) {
                        coldestSoFar = currentRow; 
                        fileName = f.getName();
                    }
                } 
            }
        }
        System.out.println( "Coldest temp was  " + coldestSoFar.get("TemperatureF") + " at " + coldestSoFar.get("TimeEST") + " " +  coldestSoFar.get("DateUTC"));
        System.out.println( "Coldest day was in file " + fileName );
        return fileName;
    }
    
    public void testFileWithColdestTemperature() {
        String coldestTempFile = fileWithColdestTemperature();
        System.out.println( "Coldest day was in file " + coldestTempFile );
        FileResource fr = new FileResource (coldestTempFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTempInADay = coldestHourInFile(parser); 
        System.out.println( "The coldest temp at  " + coldestTempInADay.get("TimeEST") + "  was  " + coldestTempInADay.get("TemperatureF"));
        System.out.println( "All the Temperatures on the coldest day were:");
        for (CSVRecord record : parser) {
            System.out.println( record.get("TemperatureF"));
        }             
    }
    
    private double averageTemperatureInFile(CSVParser parser) {
        Double avreageTemp = 0.0;
        int count = 0;
        for(CSVRecord currentRow : parser) {
            count += 1;
            avreageTemp = avreageTemp + Double.parseDouble(currentRow.get("TemperatureF"));
        } 
        avreageTemp = avreageTemp/count;
        return avreageTemp;
    }
        
    public void testAverageTemperatureInFile() {
        double averageTemperature = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource (f);
            CSVParser parser = fr.getCSVParser();
            averageTemperature = averageTemperature + averageTemperatureInFile(parser); 
        }
        System.out.println( "Average temperature in files is " + averageTemperature);
    }
}
