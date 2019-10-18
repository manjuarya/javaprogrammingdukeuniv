
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> typeMovie = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            
            Movie mv = new Movie(id, title, year, genres, director, country, poster, minutes);
            
            typeMovie.add(mv);
        }
        return typeMovie; 
    } 
    
    public void testLoadMovies() { 
        int count = 0;
        String fname = "data/ratedmoviesfull.csv";
        ArrayList<Movie> typeMovie = loadMovies(fname);
        System.out.println("The total movies in the file are: " + typeMovie.size());
        for (int i=0; i<typeMovie.size(); i++) {
            //System.out.println(typeMovie.get(i));
        }
        
        //movies for a perticular genre
        for (int i=0; i<typeMovie.size(); i++) {
            if (typeMovie.get(i).getGenres().contains("Comedy")) {
                //System.out.println(typeMovie.get(i));
                count += 1;
            }            
        }
        System.out.println("There are total " + count + " movies for comedy genre");
        
        //movies greater than some length
        count = 0;
        for (int i=0; i<typeMovie.size(); i++) {
            if (typeMovie.get(i).getMinutes() > 150) {
                //System.out.println(typeMovie.get(i));
                count += 1;
            }            
        }
        System.out.println("There are total " + count + " movies of greater than 150 mins length");
        
        //Find out which director has done most movies and movies count
        HashMap<String, ArrayList<String>> directorsMovies = new HashMap<String, ArrayList<String>>();
        for (int i=0; i<typeMovie.size(); i++) {
            String director = typeMovie.get(i).getDirector();
            String[] dirList = director.split(", "); 
            ArrayList<String> movie = new ArrayList<String>();            
            
            for(int x = 0; x < dirList.length; x++)
            {
            
                if (!directorsMovies.containsKey(dirList[x])){                
                    movie.add(typeMovie.get(i).getTitle());                
                    directorsMovies.put(dirList[x], movie);
                } 
                else {
                    ArrayList<String> st = directorsMovies.get(dirList[x]);
                    if(!st.contains(typeMovie.get(i).getTitle())){
                        st.add(typeMovie.get(i).getTitle());  
                    }                    
                    directorsMovies.put(dirList[x], st);
                }
            }
        }
        
        /*for(String s : directorsMovies.keySet()){ 
            System.out.println(s + "   " + directorsMovies.get(s).size() + "  " + directorsMovies.get(s));
        }*/
        
        int maxMovie = 0;
        String directorName = "";
        for (String s : directorsMovies.keySet()) {
            if(maxMovie < directorsMovies.get(s).size()){
                maxMovie = directorsMovies.get(s).size();
                directorName = s;
            }
        } 
        System.out.println("The maximum movies in this file is " + maxMovie + " is directed by " + directorName);        
    } 
    
    public ArrayList<Rater> loadRaters (String filename) {
        ArrayList<Rater> RaterDetail = new ArrayList<Rater>();
        FileResource fr = new FileResource( filename);
        CSVParser parser = fr.getCSVParser();
        int counter = 0;
        for (CSVRecord record : parser) {
            String item= record.get("movie_id");
            double value= Double.parseDouble(record.get("rating"));           
            String myID = record.get("rater_id");
            int flag = 0;
            if(counter == 0) {
                Rater rater = new EfficientRater(myID);
                rater.addRating(item, value);
                RaterDetail.add(rater);
            }
            else
            {
                int size = RaterDetail.size();
                for(int i=0; i<size; i++){
                    if (myID.equals(RaterDetail.get(i).getID())){
                        Rater rater1 = RaterDetail.get(i);
                        rater1.addRating(item, value);
                        flag = 1;
                    }
                    
                }
                
                if(flag == 0)
                {
                    Rater rater = new EfficientRater(myID);
                    rater.addRating(item, value);
                    RaterDetail.add(rater);
                }
                
            }
            counter++;
            flag = 0;
            
        }
        return RaterDetail; 
    } 
    
    private int findRatingCount(ArrayList<Rater> RaterDetail) {
        int size = 0;
        for(int i=0; i<RaterDetail.size(); i++)
        {
            Rater rater = RaterDetail.get(i);
            size = size + rater.getItemsRated().size();
            
        }
        return size;
    }
    
    private void noOfRatingOfaRater(ArrayList<Rater> RaterDetail, String raterID){
        for (int i=0; i<RaterDetail.size(); i++) {
            if(raterID.equals(RaterDetail.get(i).getID())) {
                System.out.print("For the Rater ID: " + RaterDetail.get(i).getID());
                System.out.println(" the total ratings are: " + RaterDetail.get(i).numRatings());
                System.out.println("Which are: " + RaterDetail.get(i).getMyRatings());   
            }                               
        }
    }
    
    private void maximumNoOfRating(ArrayList<Rater> RaterDetail){
        int maxRating = 0;
        String maxRater = "";
        ArrayList<Rating> ms = new ArrayList<Rating>();
        for (int i=0; i<RaterDetail.size(); i++) {
            if(maxRating < RaterDetail.get(i).numRatings()){
                maxRating = RaterDetail.get(i).numRatings();
                maxRater = RaterDetail.get(i).getID();
                ms = RaterDetail.get(i).getMyRatings();
            }    
        }
        System.out.println("The Rater " + maxRater + " has the maximum rating " + maxRating + "  Which are: " + ms);
    }
    
    private void totalRaterDetail(ArrayList<Rater> RaterDetail){
        for (int i=0; i<RaterDetail.size(); i++) {
            System.out.print("For the Rater ID: " + RaterDetail.get(i).getID());
            System.out.println(" the total ratings are: " + RaterDetail.get(i).numRatings());
            System.out.println("Which are: " + RaterDetail.get(i).getMyRatings());
            /*ArrayList<Rating> rating = RaterDetail.get(i).myRatings;
            for(int k=0; k<rating.size(); k++) {
                System.out.println(rating.get(k).getItem() + "  " + rating.get(k).getValue());
            }*/            
        } 
    }
    
    private void movieRaters(ArrayList<Rater> RaterDetail, String item){
        int countRaters = 0;
        for (int i=0; i<RaterDetail.size(); i++) {
            if(RaterDetail.get(i).hasRating(item)) {
                countRaters += 1;
            }
        }
        System.out.println("The total raters rated the movie " + item + " are " + countRaters);
    }
    
    private void totalDifferentMoviesRated(ArrayList<Rater> RaterDetail){
        ArrayList<String> differentMovies = new ArrayList<String>();
        for (int i=0; i<RaterDetail.size(); i++) {
            ArrayList<Rating> rating = RaterDetail.get(i).getMyRatings();
            for(int k=0; k<rating.size(); k++) {
                if(!differentMovies.contains(rating.get(k).getItem())) {
                    differentMovies.add(rating.get(k).getItem());
                }
            }
        }
        System.out.println("The total different movies rated are " + differentMovies.size());
    }   
    
    public void testLoadRaters() {
        String fname = "data/ratings.csv";
        ArrayList<Rater> RaterDetail = loadRaters(fname);
        System.out.println("The total raters in the file are: " + RaterDetail.size()); 
        
        int totalRatings = findRatingCount(RaterDetail);
        System.out.println("The total Ratings in the file are: " + totalRatings);
        
        noOfRatingOfaRater(RaterDetail, "193");
        maximumNoOfRating(RaterDetail);        
        movieRaters(RaterDetail, "1798709");
        totalDifferentMoviesRated(RaterDetail);
    } 
}
