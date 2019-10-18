package FirstCSVExample;


/**
 * Write a description of WhoExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhoExport {
    public void listExporters ( CSVParser parser, String ExportItem1, String ExportItem2) {
        for ( CSVRecord record : parser) {
            String export = record.get ("Exports");
            if ( export.contains(ExportItem1) && export.contains(ExportItem2)) {
                String country = record.get("Country");
                System.out.println ( country );
            }
        }
    }
    
    public void countryInfo ( CSVParser parser, String countryName ) {
        for ( CSVRecord record : parser) {
            String countryDetail = record.get ("Country");
            if ( countryDetail.contains(countryName)) {
                //String countr = record.get("Country");
                System.out.println ("I m in Loop");
                System.out.println ( record.get("Country")+" "+record.get("Exports")+" "+ record.get("Value (dollars)") );
                //System.out.println ( record.get("Country")+" "+record.get("Exports"));
            }
        }
    }
    
    public void numberOfExporters (CSVParser parser, String exportItem) {
        int count = 0;
        for ( CSVRecord record : parser) {
            String export = record.get ("Exports");
            if ( export.contains(exportItem)) {
                String country = record.get("Country");
                System.out.println ( country );
                count = count + 1;
            }
            //System.out.println ( "There is no Item in the list" );
        }
        //System.out.println ( "There is no Item in the list" );
        System.out.println ( "There are total " + count +"   countries which export "+ exportItem );
    }
    
    public void bigExporters ( CSVParser parser, String amount ) {
        for ( CSVRecord record : parser ) {
            String value = record.get("Value (dollars)");
            int amt = amount.length();
            int val = value.length();
            System.out.println("amt " + amt);
            System.out.println("val " + val);
            if ( val > amt) {
                System.out.println(record.get("Country") + "   " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void whoExportCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters ( parser, "gold", "diamonds");
        //countryInfo ( parser, "Madagascar");
        //numberOfExporters (parser, "gold");
        bigExporters ( parser, "$999,999,999" );
    }
   
}

