package database;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import mediaTypes.Media;
import mediaTypes.Movie;
/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Creates and abstracts a list of Movie objects and provides methods for the manipulation of the list. 
 * </P>
 * @version 1.0
 * 
 */
public class MovieDatabase {
	/**Creates a Linked List of Movie objects**/
	private LinkedList<Movie> MovieList;
	
	/** Constructs an abstracted list of Movies in a MovieDatabase object.*/
	public MovieDatabase(){
		MovieList = new LinkedList<Movie>();
	}
	/**
	 * Allows the object to call Collections.sort on its internal list
	 */
	public void internalSort(){
		Collections.sort(MovieList);
	}
	public int size(){
		return MovieList.size();
	}
	/**
	 * Allows the addition of movie objects to the movie list
	 * @param Movie Takes a movie used for addition to the list
	 */
	public void addMovie(Movie Movie){
		MovieList.add(Movie);
	}
	public LinkedList<Movie> getList(){
		return this.MovieList;
	}
	/**
	 * Searches the internal movie list for all partial title matches
	 * @param title The fragment of title used in searching
	 * @return returns an arrayList of type media of all the partial title matches
	 */
	public ArrayList<Media> searchPartialMovieTitles(String title){
		
		ArrayList<Media> results = new ArrayList<Media>();
		for(Movie m : this.MovieList){
			if(m.getTitle().contains(title)){
				results.add(m);
			}
		}
		return results;
	}
	public ArrayList<Movie> toArrayList(){
		ArrayList<Movie> movieList = new ArrayList<Movie>(MovieList.size());
		for(Movie M : MovieList){
			movieList.add(M);
		}
		return movieList;
	}
	/**
	 * Allows the user to pull ALL titles of media/movie from a given arraylist
	 * @param titles The title fragment desired
	 * @return Returns an arrayList of every title from the given media arraylist
	 */
	public String[] getAllMovieTitles(ArrayList<Media> titles){
		//TESTED: Returns the titles properly
		String[] movieTitles = new String[titles.size()];
		int counter = 0;
		for(Media m : titles){
			movieTitles[counter] = m.getTitle();
			counter++;
		}
		return movieTitles;
	}
	/**
	 * Searches for the exact movie title
	 * @param searchedTitle The title fragment that is searched
	 * @return returns the arrayList of movies that met the exact movie match
	 */
	public ArrayList<Media> searchExactMovieTitles(String searchedTitle) {
		ArrayList<Media> copyList = new ArrayList<Media>(this.MovieList);
		String[] copyListTitles = new String[this.MovieList.size()];
		ArrayList<Media> results = new ArrayList<Media>();
		//is set to a new array that contains all titles of series
		copyListTitles = getAllMovieTitles(copyList);
		//Sorts the array in case the user is bad.
		Collections.sort(this.MovieList);
		//Finds an index in which title is an exact match
		while(Arrays.binarySearch(copyListTitles, searchedTitle) > 0){
			//Stores the index found
			int indexFound = Arrays.binarySearch(copyListTitles, searchedTitle);
			//Adds the series to the results arrayList
			results.add(copyList.get(indexFound));
			//Removes the found Series from the list
			copyList.remove(indexFound);
			//Constructs a new string array and repeats the loop
			copyListTitles = getAllMovieTitles(copyList);
		}
		return results;
	}
	/**
	 * Searched the movie list array for all year matches
	 * @param yearsSearched the years that are searched
	 * @return returns an arraylist of media for every movie that matched the list of given years
	 */
	public ArrayList<Media> searchMovieYears(ArrayList<Integer> yearsSearched) {
		ArrayList<Media> results = new ArrayList<Media>();
		for(Integer i : yearsSearched){
			for(Movie m : MovieList){
				if(m.getStartYear() == i){
					results.add(m);
				}
			}
		}
		return results;
	}
	
	public static String formatFriendlyText(ArrayList<Movie> finalModifiedResults) {
		String display = "";
		for(Media m : finalModifiedResults){
			display += m.getFileFormattedName() + "\n";
		}
		return display;
	}
	
	public void addAllMovies(ArrayList<Movie> movieList2) {
		MovieList.addAll(movieList2);
	}
	
	public void editMovie(int index, Movie m) {
		MovieList.set(index, m);
	}
	public void deleteMovie(int index) {
		MovieList.remove(index);
		
	}
}
