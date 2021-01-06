
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile,String ratingfile){
        FirstRatings fr=new FirstRatings();
        myMovies=fr.loadMovies("data/"+moviefile);
        myRaters=fr.loadRaters("data/"+ratingfile);
    }
    public int getMovieSize(){
        return myMovies.size();
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    private double getAverageByID(String ID,int minimalRaters){
        double average=0,total=0;
        int countRater=0;
        for(Rater r : myRaters){
            if(r.hasRating(ID)){
                countRater+=1;
                total+=r.getRating(ID);
            }
        }
        if(countRater>= minimalRaters){
            average=total/countRater;
        }
        return average;
    }
    public ArrayList<Rating> getAverageRating(int minimalRaters){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        for(Movie mv : myMovies){
            String id=mv.getID();
            double value=getAverageByID(id,minimalRaters);
            if(value>0.0){
                Rating rg=new Rating(id,value);
                ans.add(rg);
            }
        }
        return ans;
    }
    public String getTitle(String id){
        for(Movie mv : myMovies){
            if(mv.getID().equals(id)){
                return mv.getTitle();
            }
        }
        return "ID was not Found";
    }
    public String getID(String title){
        for(Movie mv : myMovies){
            if(mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
