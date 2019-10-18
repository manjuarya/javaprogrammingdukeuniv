
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
	for (CSVRecord rec : fr.getCSVParser(false)) {
	    int numBorn = Integer.parseInt(rec.get(2));
	    if (numBorn <= 100) {
		System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
	    }
        }
    }

    private void totalBirths (FileResource fr) {
	int totalBirths = 0;
	int totalBoys = 0;
	int totalGirls = 0;
	int noOfBoysBorn = 0;
	int noOfGirlsBorn = 0;
	for (CSVRecord rec : fr.getCSVParser(false)) {
	    int numBorn = Integer.parseInt(rec.get(2));
	    totalBirths += numBorn;
	    if (rec.get(1).equals("M")) {
		totalBoys += numBorn;
		noOfBoysBorn += 1;
            }
            else {
                totalGirls += numBorn;
                noOfGirlsBorn += 1;
            }
	}
	System.out.println("total births = " + totalBirths);
	System.out.println("female girls = " + totalGirls + " & total girls born " + noOfGirlsBorn);
	System.out.println("male boys = " + totalBoys + " & total boys born " + noOfBoysBorn);
    }

    public void testTotalBirths () {
	//FileResource fr = new FileResource();
	FileResource fr = new FileResource();
	totalBirths(fr);
    }
    
    private int getRank(int year, String name, String gender) {        
        int rank = 0;        
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser =fr.getCSVParser(false);
        for (CSVRecord rec : parser) {            
            if (rec.get(1).equals(gender)){
                rank += 1;
                if (rec.get(0).equals(name)) {
                    System.out.println("Name " + name + " rank is  " + rank + " in the year " + year);
                    //System.out.println( rank);
                    return rank;
                }
            }            
        }
        return 0;
    } 

    
    public void TestGetRank() {
       int year = 1971;
       String name = "Frank";
       String gender = "M";
       int rank = getRank(year, name, gender);
       System.out.println("The rank of the name " + name + " in the year " + year + " is " + rank);       
    }
    
    private String getName(int year, int rank, String gender){
        int count = 0;
        String name = "";
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser =fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)){
                count += 1;
                if (count == rank) {
                    name = rec.get(0);
                    System.out.println("In the year " +year + " the rank " + rank + " name is " + name);
                    return name;
                }
            }    
        } 
        return "NoName";
    } 
    
    public void TestGetName() {
       int year = 1982;
       int rank = 450;
       String gender = "M";
       String name = getName(year, rank, gender);
       System.out.println("In the year " +year + " the rank " + rank + " name is " + name);       
    }
     
    private void whatIsNameInYear(int year, String name, String gender, int newYear) {
        int rank = getRank(year, name, gender);
        System.out.println("The rank of the name " + name + " in the year " + year + " is " + rank); 
        String nameFuture = getName(newYear, rank, gender);
        System.out.println("In the year " +newYear + " the rank " + rank + " name is  " + nameFuture);     
    } 
    
    public void TestWhatIsNameInYear() {
       int year = 1974;
       String name = "Owen";
       int newYear = 2014;
       String gender = "M";
       whatIsNameInYear(year, name, gender, newYear);      
    }
    
    private void yearOfHighestRank(String name, String gender) {
        int highestRank = 1000000;
        int currentRank = 0;
        int yearOfHighestRank = 0;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {            
            String fname = f.getName();
            String str = fname.substring(3, 7);
            year = Integer.parseInt(str);
            currentRank = getRank(year, name, gender);
            if (highestRank > currentRank) {
                highestRank = currentRank;
                yearOfHighestRank = year;
            }
        }
        System.out.println("For name " + name + " the highest rank is " + highestRank + " in the year " + yearOfHighestRank);
    }  
    
    public void testYearOfHighestRank() {
        String name = "Mich";
        String gender = "M";
        yearOfHighestRank(name, gender);
    }
    
    private void getAverageRank (String name, String gender) {
        int rankTotal = 0;
        int currentRank = 0;      
        int count = 0;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {   
            count += 1;
            String fname = f.getName();
            String str = fname.substring(3, 7);
            year = Integer.parseInt(str);
            currentRank = getRank(year, name, gender);
            rankTotal += currentRank;            
        }
        double averageRank = (double)rankTotal/count;
        System.out.println("The average rank for the name " + name + " is " + averageRank);
    }
    
    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        getAverageRank(name, gender);
    }
    
    private int totalBirthsWithName(String name, int year, String gender) {
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser =fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)){
                String str = rec.get(2);
                int births = Integer.parseInt(str);
                return births;                
            }    
        }
        return 0;
    }
    
    private void getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirthsRankedHigher = 0;
        String nameOfHigherRankPerson = "";
        int rank = getRank(year, name, gender);
        for (int i= rank-1; i>0; i--) {
            nameOfHigherRankPerson = getName(year, i, gender);
            totalBirthsRankedHigher += totalBirthsWithName(nameOfHigherRankPerson, year, gender);
        }
        System.out.println("The total births higher than " + name + " rank " + rank + " is " + totalBirthsRankedHigher);
    } 
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Emily";
        String gender = "F";
        getTotalBirthsRankedHigher(year,name, gender);
    }
}
