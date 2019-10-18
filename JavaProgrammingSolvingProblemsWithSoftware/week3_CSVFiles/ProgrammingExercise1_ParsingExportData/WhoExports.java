
/**
 * Write a description of WhoExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhoExports {
      
    public void bigExporters ( CSVParser parser, String amount ) {
        int count = 0;
        for ( CSVRecord record : parser ) {
            String value = record.get("Value (dollars)");
            int amt = amount.length();
            int val = value.length();
            //System.out.println("amt " + amt);
            //System.out.println("val " + val); 
            if ( val > amt) {
                System.out.println(record.get("Country") + "   " + record.get("Value (dollars)"));
                count += 1;
            }
        }
        System.out.println("There are total " + count + "  countries, whose amount is geater than " + amount);
    }
    
    public int numberOfExporters (CSVParser parser, String exportItem) {
        int count = 0;
        for ( CSVRecord record : parser) {
            String export = record.get ("Exports");
            if ( export.contains(exportItem)) {
                String country = record.get("Country");
                System.out.println ("The country which export" + exportItem + " is " + country );
                count = count + 1;
            }            
        }
        //System.out.println ( "There is no Item in the list" );
        //System.out.println ( "There are total " + count +"   countries which export "+ exportItem );
        return count;
    }
    
    public void listExportersTwoProducts  ( CSVParser parser, String ExportItem1, String ExportItem2) {
        for ( CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(ExportItem1)) {
                if (export.contains(ExportItem2)) {
                   String country = record.get("Country");
                   System.out.println ( "The country which export both items are " + country);
                }            
            }
        }
    }
    
    public String countryInfo ( CSVParser parser, String country ) {
        for ( CSVRecord record : parser) {
            String countryDetail = record.get ("Country");
            if ( countryDetail.contains(country)) {              
                //System.out.println ( record.get("Country") + ":  " + record.get("Exports") + ":  "+ record.get("Value (dollars)") );
                return record.get("Country") + ":  " + record.get("Exports") + ":  "+ record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExportersTwoProducts ( parser, "sugar ", "rum");
        //String str = countryInfo ( parser, "Nauru");
        //System.out.println ( str );        
        //String exportItem = "flowers";
        //int noOfCountries = numberOfExporters(parser, exportItem);
        //System.out.println ("There are total " + noOfCountries + "  countries, which export  " +  exportItem);
        String amount = "$1,000,000,000,000";
        System.out.println ("The big exporters for amount " + amount + " are: ");
        bigExporters ( parser, amount );
    }
}
