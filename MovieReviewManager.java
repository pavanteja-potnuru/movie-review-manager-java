import java.time.LocalDate;
import java.util.*;
import reviewmanager.factory.*;
import reviewmanager.factory.impl.*;
import reviewmanager.model.Color;
import reviewmanager.services.*;
import reviewmanager.utils.*;

/**
 * Movie review manager class
 */
public class MovieReviewManager {
    private static IServiceLogger serviceLogger;

    private static IDataFactory dataFactory;

    private static IUserManager userManager;
    private static IMovieManager movieManager;
    private static IReviewManager reviewManager;
    private static IServiceFactory serviceFactory;
    
    static {
        // utilities
        serviceLogger = new ServiceLogger();

        // datastores
        dataFactory = new DataFactory();

        // services
        serviceFactory = new ServiceFactory(serviceLogger, dataFactory);
        userManager = serviceFactory.getUserManager();
        movieManager = serviceFactory.getMovieManager();
        reviewManager = serviceFactory.getReviewManager();
    }
    public static void main(String[] args) {
        try{
            userManager.addUser("Pavan");
            userManager.addUser("Yesh");
            userManager.addUser("Harsh");
            userManager.addUser("Srinivas");
            userManager.addUser("Harsh");

            movieManager.addMovie("Bahubali1", LocalDate.of(2015, 7, 10), Arrays.asList("action", "fantasy"));
            movieManager.addMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
            movieManager.addMovie("Don", LocalDate.of(2006, 7, 10), Arrays.asList("action", "comedy"));
            movieManager.addMovie("Bahubali2", LocalDate.of(2017, 4, 28), Arrays.asList("action", "fantasy"));
            movieManager.addMovie("Tiger", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
            movieManager.addMovie("Padmaavat", LocalDate.of(2008, 1, 1), Arrays.asList("Comedy"));
            movieManager.addMovie("Lunchbox", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
            movieManager.addMovie("Guru", LocalDate.of(2008, 1, 1), Arrays.asList("Drama"));
            movieManager.addMovie("Metro", LocalDate.of(2008, 1, 1), Arrays.asList("Romance"));
            movieManager.addMovie("Saina", LocalDate.of(2021, 03, 26), Arrays.asList("Sport"));

            reviewManager.addReview("Pavan", "Bahubali1", 10);
            reviewManager.addReview("Pavan", "Padmaavat", 9);
            reviewManager.addReview("Pavan", "Guru", 8);
            reviewManager.addReview("Pavan", "Lunchbox", 8);
            reviewManager.addReview("Pavan", "Bahubali2", 10);
            reviewManager.addReview("Pavan", "Metro", 9);
            reviewManager.addReview("Pavan", "Saina", 8);
            
            reviewManager.addReview("Yesh", "Bahubali1", 9);
            reviewManager.addReview("Yesh", "Bahubali2", 8);
            reviewManager.addReview("Yesh", "Padmaavat", 8);
            reviewManager.addReview("Harsh", "Guru", 10);
            reviewManager.addReview("Harsh", "Lunchbox", 9);
            reviewManager.addReview("Harsh", "Metro", 7);
            reviewManager.addReview("Harsh", "Bahubali2", 10);
            reviewManager.addReview("Harsh", "Tiger", 9);
        }
        catch (Exception ex) {
            serviceLogger.logError(ex.getMessage(), Color.ANSI_RED);
        }
        finally {
            System.out.println("------------------------/Print Users\\------------------------");
            userManager.printUsers();
            System.out.println("------------------------/Print Movies with average rating\\------------------------");
            movieManager.printMovies();
            System.out.println("------------------------/Print top n rated Movies byRole and inGenre\\------------------------");
            printList(reviewManager.topNMoviesWithRoleGenre(2, "critic", "Drama"));
            System.out.println("------------------------/Print average review\\------------------------");
            System.out.println(movieManager.getAverageRating("Bahubali1"));
            System.out.println(movieManager.getAverageRating("Bahubali2"));
            System.out.println("------------------------/Print average review given by user to movies released in a particular year\\------------------------");
            System.out.println(reviewManager.getAverageReview("Pavan", 2008));
            System.out.println("------------------------/Print top n rated movies\\------------------------");
            printList(movieManager.getTopNRatedMovies(3));
            System.out.println("------------------------/\\------------------------");
        }
    }

    private static void printList(List<String> list) {
        list.forEach(element -> System.out.println(element));
    }
}