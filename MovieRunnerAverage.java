
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv";
        SecondRatings sc=new SecondRatings(moviename,ratername);
        System.out.println("Number of Movies: "+ sc.getMovieSize());
        System.out.println("Number of Raters: "+ sc.getRaterSize());
        
        int minimalRaters=50;
        ArrayList<Rating> avgRating=sc.getAverageRating(minimalRaters);
        Collections.sort(avgRating);
        for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" "+sc.getTitle(rg.getItem()));
        }
        System.out.println("Movies with"+minimalRaters+" or more: "+avgRating.size());
    }
    public void getAverageRatingOneMovie(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv";
        SecondRatings sc=new SecondRatings(moviename,ratername);
        String title="Vacation";
        String id=sc.getID(title);
        int minimalRaters=0;
        ArrayList<Rating> avgRating=sc.getAverageRating(minimalRaters);
        for(Rating rg : avgRating){
            if(rg.getItem().equals(id)){
                System.out.println("Average rating for '" + title + "' is: " + rg.getValue());
           
             }
          }
    }
}
