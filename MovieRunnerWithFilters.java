
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=35;
        ArrayList<Rating> avgRating=tr.getAverageRating(minimalRaters);
        Collections.sort(avgRating);
        System.out.println("Number of movies with minimal raters: "+avgRating.size());
        for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" "+MovieDatabase.getTitle(rg.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=20;
        int year=2000;
        Filter yf = new YearAfterFilter(year);
        ArrayList<Rating> avgRating=tr.getAverageRatingsByFilter(minimalRaters,yf);
        Collections.sort(avgRating);
        System.out.println("Number of movies with minimal raters and applied filter: "+avgRating.size());
        for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" "+MovieDatabase.getYear(rg.getItem())+" "+MovieDatabase.getTitle(rg.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=20;
        String genre="Comedy";
        Filter gf=new GenreFilter(genre);
        ArrayList<Rating> avgRating=tr.getAverageRatingsByFilter(minimalRaters,gf);
        Collections.sort(avgRating);
        System.out.println("Number of movies with minimal raters and applied filter: "+avgRating.size());
        for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" "+MovieDatabase.getTitle(rg.getItem()));
            System.out.println("   "+MovieDatabase.getGenres(rg.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=5;
        int minMinutes=105,maxMinutes=135;
        Filter mf=new MinutesFilter(minMinutes,maxMinutes);
        ArrayList<Rating> avgRating=tr.getAverageRatingsByFilter(minimalRaters,mf);
        Collections.sort(avgRating);
        System.out.println("Number of movies with minimal raters and applied filter: "+avgRating.size());
        /*for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" Time: "+MovieDatabase.getMinutes(rg.getItem())+" "+MovieDatabase.getTitle(rg.getItem()));
            
        }*/
    }
    
    public void  printAverageRatingsByDirectors(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=4;
        String directors="Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter mf=new DirectorsFilter(directors);
        ArrayList<Rating> avgRating=tr.getAverageRatingsByFilter(minimalRaters,mf);
        Collections.sort(avgRating);
        System.out.println("Number of movies with minimal raters and applied filter: "+avgRating.size());
        /*for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" "+MovieDatabase.getTitle(rg.getItem()));
            System.out.println("    "+MovieDatabase.getDirector(rg.getItem()));          
        }*/
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=8;
        int year=1990;
        String genre="Drama";
        Filter yf = new YearAfterFilter(year);
        Filter gf=new GenreFilter(genre);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(yf);
        filtersList.addFilter(gf);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,filtersList);
        Collections.sort(list);
        System.out.println("Number of movies with minimal raters and applied filter: "+list.size());
        /*for(Rating r : list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Genre = MovieDatabase.getGenres(r.getItem());
            int Year = MovieDatabase.getYear(r.getItem());

            System.out.println(r.getValue()+ " " + Year +" "+ Title );
            System.out.println("       "+ Genre);
        }*/
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        String moviename="ratedmoviesfull.csv";
        String ratername="ratings.csv ";
        ThirdRatings tr=new ThirdRatings(ratername);
        System.out.println("Number of Raters: "+ tr.getRaterSize());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=3;
        String directors="Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        int maxMinutes=180,minMinutes=90;
        Filter mf=new MinutesFilter(minMinutes,maxMinutes);
        Filter df=new DirectorsFilter(directors);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(mf);
        filtersList.addFilter(df);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(minimalRaters,filtersList);
        Collections.sort(list);
        System.out.println("Number of movies with minimal raters and applied filter: "+list.size());
        for(Rating r : list){
            String Title = MovieDatabase.getTitle(r.getItem());
            int minute=MovieDatabase.getMinutes(r.getItem());
            String director=MovieDatabase.getDirector(r.getItem());
            System.out.println(r.getValue()+" Time: "+minute+" "+Title);
            System.out.println("    "+director);
        }
    }
}
