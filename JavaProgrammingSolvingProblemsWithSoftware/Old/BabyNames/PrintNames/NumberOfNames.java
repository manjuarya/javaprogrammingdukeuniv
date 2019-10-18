
/**
 * Write a description of NumberOfNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class NumberOfNames {

    public int printNames (FileResource fr) {
        int count = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")){
                System.out.println("Name of the Boy is  " + rec.get(0) );
            }
            else {
                System.out.println("Name of the Girl is  " + rec.get(0) );
            }
            count+=1;
        }
        return count;
    }

    
    public void getNumberOfNames() {
       int totalNames;
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource (f);
            totalNames = printNames(fr);
            System.out.println("There are total  " + totalNames + "  Names in the file  " + f.getName()  );
       }
    }
    
   }
