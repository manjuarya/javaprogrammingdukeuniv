
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings () {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        int minimalRater = 35;
        ArrayList<Rating> avgRating = tr.getAverageRatings(minimalRater);
        Collections.sort(avgRating); 
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is " + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem()));
                count += 1;
            }            
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
    
    public void printAverageRatingsByYear() {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        int minimalRater = 20;
        int year = 2000;
        YearAfterFilter YAF = new YearAfterFilter(year);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minimalRater, YAF);
        Collections.sort(avgRating);
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is " + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem())
                                   + " " + MovieDatabase.getYear(avgRating.get(i).getItem()));
                count += 1;
            }            
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
    
    public void printAverageRatingsByGenre () {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        int minimalRater = 20;
        String genre = "Comedy";
        GenreFilter GF = new GenreFilter(genre);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minimalRater, GF);
        Collections.sort(avgRating); 
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is " + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem())
                                   + " " + MovieDatabase.getGenres(avgRating.get(i).getItem()));
                count += 1;
            }            
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
    
    public void printAverageRatingsByMinutes () {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        int minimalRater = 5;
        int minNoMinutes = 105;
        int maxNoMinutes = 135;
        MinutesFilter  MF = new MinutesFilter(minNoMinutes, maxNoMinutes);
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minimalRater, MF);
        Collections.sort(avgRating); 
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is " + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem())
                                   + " " + MovieDatabase.getMinutes(avgRating.get(i).getItem()));
                count += 1;
            }            
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
    
    public void printAverageRatingsByDirectors  () {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        int minimalRater = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter  DF = new DirectorsFilter(directors); 
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minimalRater, DF);
        Collections.sort(avgRating); 
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is  " 
                            + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem())
                                   + " " + MovieDatabase.getDirector(avgRating.get(i).getItem()));
                count += 1;
            }        
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
    
    public void printAverageRatingsByYearAfterAndGenre  () {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        
        int minimalRater = 8;
        
        int year = 1990;
        YearAfterFilter YAF = new YearAfterFilter(year);
        String genre = "Drama";
        GenreFilter GF = new GenreFilter(genre);
        
        AllFilters filter = new AllFilters();
        filter.addFilter(YAF);
        filter.addFilter(GF);
        
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minimalRater, filter);
        Collections.sort(avgRating); 
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is  " 
                            + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem())
                + " " + MovieDatabase.getGenres(avgRating.get(i).getItem()) + " " + MovieDatabase.getYear(avgRating.get(i).getItem()));
                count += 1;
            }        
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
    
    public void printAverageRatingsByDirectorsAndMinutes () {
        String moviefile = "ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        ThirdRatings  tr = new ThirdRatings (ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + tr.getRaterSize());
        
        int minimalRater = 3;
        
        int minNoMinutes = 90;
        int maxNoMinutes = 180;
        MinutesFilter  MF = new MinutesFilter(minNoMinutes, maxNoMinutes);
        
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter  DF = new DirectorsFilter(directors);         
        
        AllFilters filter = new AllFilters();
        filter.addFilter(MF);
        filter.addFilter(DF);
        
        ArrayList<Rating> avgRating = tr.getAverageRatingsByFilter(minimalRater, filter);
        Collections.sort(avgRating); 
        int count = 0;
        System.out.println("The  number of movies found for average rating with minimal rater " + minimalRater + " is  " 
                            + avgRating.size());
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + MovieDatabase.getTitle(avgRating.get(i).getItem())
                + " " + MovieDatabase.getMinutes(avgRating.get(i).getItem()) + " " + MovieDatabase.getDirector(avgRating.get(i).getItem()));
                count += 1;
            }        
        }
        System.out.println("The total movies rated more than 0 ratings is: " + count);
    } 
}
