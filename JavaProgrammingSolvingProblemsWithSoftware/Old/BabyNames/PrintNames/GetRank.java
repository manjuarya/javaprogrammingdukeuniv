
/**
 * Write a description of GetRank here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class GetRank {

    public int getRankName (FileResource fr) {
        int count = 0;
        int value = 0;
        int rankG = 0;
        CSVParser parser =fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals("M")){
                value+=1;
                System.out.println("Name of the Boy is  " + rec.get(0) + "  & his rank is  " + value);
            }
            else {
                rankG+=1;
                System.out.println("Name of the Girl is  " + rec.get(0) + "  & her rank is  " + rankG);
            }
            count+=1;
        }
        return count;
    }

    
    public void TestRank() {
       int totalNames;
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource (f);
            totalNames = getRankName(fr);
            System.out.println("There are total  " + totalNames + "  Names in the file  " + f.getName()  );
       }
    }
}
