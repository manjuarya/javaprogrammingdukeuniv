package FirstCSVExample;


/**
 * Write a description of WhichCountryExportCoffee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountryExportCoffee {
    public void listExporters ( CSVParser parser, String ExportOfIntrest) {
        for ( CSVRecord record : parser) {
            String export = record.get ("Exports");
            if ( export.contains(ExportOfIntrest)) {
                String country = record.get("Country");
                System.out.println ( country );
            }
        }
    }
    public void whoExportCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters ( parser, "coffee");
    }
   
}
