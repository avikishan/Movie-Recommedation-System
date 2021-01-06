
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
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr=new FileResource(filename);
        ArrayList<Movie> movieList=new ArrayList<Movie>();
        for(CSVRecord record : fr.getCSVParser()){
            Movie currMovie=new Movie(record.get("id"),record.get("title"),
                                        record.get("year"),record.get("genre"),
                                      record.get("director"),record.get("country"),
                                      record.get("poster"),Integer.parseInt(record.get("minutes")));
            movieList.add(currMovie);                  
        }
        return movieList;
    }
    public void testLoadMovies(){
        String st="data/ratedmoviesfull.csv";
        ArrayList<Movie> local=loadMovies(st);
        System.out.println("Number of Movies "+local.size());
        int comedygenre=0;
        int moviemorethan=0;
        HashMap<String,Integer> director=new HashMap<String,Integer>();
        for(Movie mv  : local){
            //System.out.println(mv);
            if(mv.getGenres().contains("Comedy")){
                comedygenre+=1;
            }
            if(mv.getMinutes()>150){
                moviemorethan+=1;
            }
            String currDirector=mv.getDirector();
            if(director.containsKey(currDirector)){
                director.put(currDirector,director.get(currDirector)+1);
            }
            else{
                director.put(currDirector,1);
            }
        }
        int dirWithMaxMovies = Collections.max(director.values());
     
        ArrayList<String> movieWithMaxdirs = new ArrayList();
        for(String dir:director.keySet()){
            if(director.get(dir)==dirWithMaxMovies){
             movieWithMaxdirs.add(dir);
            }
        }
        System.out.println("The numbers of Comedy movies: "+comedygenre);
        System.out.println("The numbers of movies greater than: "+moviemorethan);
        System.out.println("Director with Max movies: " + dirWithMaxMovies);
        System.out.println("Max number of movies by a single director: "+ dirWithMaxMovies);
        System.out.println("Directors that directed the max number of movies: \n" + movieWithMaxdirs);
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr=new FileResource(filename);
        ArrayList<Rater> raters = new ArrayList<Rater>();
        ArrayList<String> raterIDList = new ArrayList();
        for(CSVRecord record : fr.getCSVParser()){
            String rater_id=record.get("rater_id");
            String movie_id=record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if(raters.size()==0){
                Rater currRater = new EfficientRater(rater_id);
                currRater.addRating(movie_id, rating);
                raters.add(currRater);
            }
            else{
                int flag=0;
                Rater currRater = new EfficientRater(rater_id);
                for(Rater r : raters){
                    if(r.getID().equals(rater_id)){
                        flag=1;
                        currRater=r;
                        break;
                    }
                }
                if(flag==1){
                 currRater.addRating(movie_id,rating);
                }
                else{
                    currRater.addRating(movie_id, rating);
                    raters.add(currRater);
                    }
             }
         }
         return raters;
        }
        

    public void testLoadRaters(){
        String st="data/ratings.csv";
        ArrayList<Rater> local=loadRaters(st);
        String idsearch="193";
        int maxrating=maxRating(local);
        int raterwithmaxrating=0;
        int ratingofgiven=0;
        System.out.println("Number of Rater: "+local.size());
        System.out.println("Maximum number of ratings by any rater: "+maxrating);
        String movie="1798709";
        int numofratingofmovie=0;
        ArrayList<String> movieList=new ArrayList<String>();
        for(Rater r : local){
            /*System.out.println("Raters id: "+r.getID()+" Number of Ratings: "+r.numRatings());
            ArrayList<String> movie=r.getItemsRated();
            for(String s : movie){
                System.out.println("Item: "+s+" Rating: "+r.getRating(s));
            }
            System.out.println();*/
            if(r.getID().equals(idsearch)){
                ratingofgiven=r.numRatings();
            }
            if(r.numRatings()==maxrating){
                System.out.println("Rater ID with max Rating: "+r.getID());
            }
            if(r.hasRating(movie)){
                numofratingofmovie+=1;
            }
            ArrayList<String> items=r.getItemsRated();
            for(String item : items){
                if(!movieList.contains(item)){
                    movieList.add(item);
                }
            }
        }
        
        System.out.println("Number of Rating of Given Rater: "+ ratingofgiven);
        System.out.println("Number of Rating of "+movie+" ID is : "+numofratingofmovie);
        System.out.println("Number of Different movies rated: "+movieList.size());
    }
    public int maxRating(ArrayList<Rater> l){
        int max=0;
        for(Rater r : l){
            if(r.numRatings()>max){
                max=r.numRatings();
            }
        }
        return max;
    }
}
