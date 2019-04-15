package common;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import database.MovieDatabase;
import database.SeriesDatabase;
import mediaTypes.Media;

/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Searches and sorts all data bases as well as providing methods for which the user can retrieve a list of found movies and 
 * series.
 * </P>
 * @version 1.0
 * 
 */
public class MediaSearchHandler {
	/**
	 * 
	 * @param movieDatabase Database of Movies
	 * @param seriesDatabase Database of Series
	 * @param inputReader Reader
	 * @return Returns the string of results
	 * @throws IOException Throws exception if user messes up
	 */
	public static String generalSearching(MovieDatabase movieDatabase, SeriesDatabase seriesDatabase, BufferedReader inputReader) 
			throws IOException{
		String mediaTypeSearched, exactOrPartialMatch = "", searchByYearOrTitle, 
			episodeTitleIncluded = "", searchedTitle = "NOPREFGIVEN", searchedYear = "-15", sortedByYearOrTitle;
		int yearForSearching = 0;
		ArrayList<Integer> yearsSearched = new ArrayList<Integer>();
		ArrayList<Media> finalResults = new ArrayList<Media>();
		
		//Prompts the user for desired search type
		System.out.println("Would you like to search (m)ovies, (s)eries, or (b)oth?");
		mediaTypeSearched = inputReader.readLine();
		
		//While not valid input
		while(!(mediaTypeSearched.equals("m") || mediaTypeSearched.equals("s") || mediaTypeSearched.equals("b"))){
			System.out.println("Please give valid input.");
			System.out.println("Would you like to search (m)ovies, (s)eries, or (b)oth?");
			mediaTypeSearched = inputReader.readLine();
		}
		
		//Prompts for how the movie will be searched
		System.out.println("Search (t)itle, (y)ear, or (b)oth?");
		searchByYearOrTitle = inputReader.readLine();
		
		//While not valid input
		while(!(searchByYearOrTitle.equals("t") || searchByYearOrTitle.equals("y") || searchByYearOrTitle.equals("b"))){
			System.out.println("Please give valid input.");
			System.out.println("Search (t)itle, (y)ear, or (b)oth?");
			searchByYearOrTitle = inputReader.readLine();
		}
		//If the user desired to search the title
		if(searchByYearOrTitle.equals("t") || searchByYearOrTitle.equals("b")){
			//If the user would like to search or exact or partial title
			System.out.println("Search for (e)xact or (p)artial matches?");
			exactOrPartialMatch = inputReader.readLine();
			
			//While invalid input
			while(!(exactOrPartialMatch.equals("e")|| exactOrPartialMatch.equals("p"))){
				System.out.println("Invalid Input");
				System.out.println("Search for (e)xact or (p)artial matches?");
				exactOrPartialMatch = inputReader.readLine();
			}
		}
		//Runs if the user wishes to search the year
		if(searchByYearOrTitle.equals("b") || searchByYearOrTitle.equals("y")){
			//Takes a range of years in "year, year, year" or "year - year" format.
			System.out.println("Year(s) to be searched? Takes 'year, year, year' or 'year - year' format");
			searchedYear = inputReader.readLine();
			
			//If user does not provide 4 digits.
			while(searchedYear.length() < 4){
				System.out.println("Invalid input. Must give atleast 4 digits.");
				System.out.println("Year(s) to be searched? Takes 'year, year, year' or 'year - year' format");
				searchedYear = inputReader.readLine();
			}
			
			//Separate search method for a single year provided
			if(searchedYear.length() == 4){
				yearForSearching = Integer.parseInt(searchedYear);
				yearsSearched.add(yearForSearching);
				//Call the method that adds to results
				
			}
			//if the user provides an array or range of numbers
			if(searchedYear.contains(",") || searchedYear.contains("-")){
				//The arrayList now contains the parsed integers from the user
				yearsSearched = parseUserYears(searchedYear);
			}
		}
		/*
		 * HANDLES SERIES SEARCHING
		 */
		if(mediaTypeSearched.equals("s") || mediaTypeSearched.equals("b")){
			if(searchByYearOrTitle.equals("t") || searchByYearOrTitle.equals("b")){
				System.out.println("Include episode titles in search and output (y/n)?");
				episodeTitleIncluded = inputReader.readLine();
				System.out.println("Enter title: ");
				searchedTitle = inputReader.readLine();
				
			}
			//While invalid input
			while(!(episodeTitleIncluded.equals("y") || episodeTitleIncluded.equals("n"))){
				System.out.println("Invalid input.");
				System.out.println("Include episode titles in search and output (y/n)?");
				episodeTitleIncluded = inputReader.readLine();
			}
		
			//Calls method that displays the searched data with the arguments given above
			finalResults.addAll(userSearchedSeries(episodeTitleIncluded, searchByYearOrTitle, searchedTitle, yearsSearched, 
					exactOrPartialMatch, seriesDatabase));
		}
		
		//Handles movie searching if movies were included as the media type
		if(mediaTypeSearched.equals("m") || mediaTypeSearched.equals("b")){
			if(searchedTitle.equals("NOPREFGIVEN")){
				System.out.println("Enter title:");
				searchedTitle = inputReader.readLine();
			}
			//System.out.println("Getting finalResults size before userSearchedMovies call: " + finalResults.size());
			finalResults.addAll(userSearchedMovies(searchByYearOrTitle, searchedTitle, yearsSearched, exactOrPartialMatch, movieDatabase));
			//System.out.println("getting final results after userSearchedMovies call " + finalResults.size());
		}
		//Sorts the data by title or year based on the users input
		System.out.println("Would you like the display data sorted by (y)ear or (t)itle?");
		sortedByYearOrTitle = inputReader.readLine();
		
		//While invalid input
		while(!(sortedByYearOrTitle.equals("y") || sortedByYearOrTitle.equals("t"))){
			System.out.println("Invalid Input");
			System.out.println("Would you like the display data sorted by (y)ear or (t)itle?");
			sortedByYearOrTitle = inputReader.readLine();
		}
		//Narrows down the database to a modified results list if the user searched both title and year.
		ArrayList<Media> finalModifiedResults = finalResults;
		if(searchByYearOrTitle.equals("b")){
			finalModifiedResults = dualSearchComparison(exactOrPartialMatch, searchedTitle, 
					yearsSearched, finalResults);
		}
		
		//Sort the data by year or title
		if(sortedByYearOrTitle.equals("y")){
			Collections.sort(finalModifiedResults, new MediaComparator(MediaComparator.SORT_BY_YEAR));
		}else{
			Collections.sort(finalModifiedResults, new MediaComparator(MediaComparator.SORT_BY_TITLE));
		}
		//Displays the data
		//Prompts the user if they would like to save the output data to a file
		//Clears the results array if the user wishes to continue searching
		System.out.println(displayTheResultsAsText(finalModifiedResults, mediaTypeSearched, exactOrPartialMatch, searchByYearOrTitle, sortedByYearOrTitle, 
				episodeTitleIncluded, searchedYear, searchedTitle));
		
		return formatFriendlyText(finalModifiedResults);
		/*return displayTheResultsAsText(finalModifiedResults, mediaTypeSearched, exactOrPartialMatch, searchByYearOrTitle, sortedByYearOrTitle, 
					episodeTitleIncluded, searchedYear, searchedTitle);
		*/
	}
	/**
	 * Converts the array list into a string that is useable in imports.
	 * @param finalModifiedResults The array of results
	 * @return Return the string of results in format friendly ways.
	 */
	private static String formatFriendlyText(ArrayList<Media> finalModifiedResults) {
		String display = "";
		for(Media m : finalModifiedResults){
			display += m.getFileFormattedName() + "\n";
		}
		return display;
	}
	/**
	 * Narrows down the results if the user decided to search both year and movie. It finds media that fits both criteria.
	 * @param exactOrPartialMatch If the user searched for an exact or partial match
	 * @param searchedTitle The title that is searched
	 * @param yearsSearched The years the user is searching
	 * @param finalResults The final array with the modified results
	 * @return Returns an arrayList of type media of the narrowed down results
	 */
	private static ArrayList<Media> dualSearchComparison(String exactOrPartialMatch, String searchedTitle,
			ArrayList<Integer> yearsSearched, ArrayList<Media> finalResults) {
		ArrayList<Media> modifiedResults = new ArrayList<Media>();
		if(exactOrPartialMatch.equals("e")){
			for(Media m : finalResults){
				if(m.getTitle().equals(searchedTitle)){
					for(Integer i : yearsSearched){
						if(i == m.getStartYear()){
							modifiedResults.add(m);
						}
					}
				}
			}
		}
		else{
			for(Media m : finalResults){
				if(m.getTitle().contains(searchedTitle)){
					for(Integer i : yearsSearched){
						if(i.equals(m.getStartYear())){
							modifiedResults.add(m);
						}
					}
				}
			}
		}
		for(int i = 0; i < modifiedResults.size(); i++){
			for(int j = i + 1; j < modifiedResults.size(); j++){
				if(modifiedResults.get(i).toString().equals(modifiedResults.get(j).toString())){
					modifiedResults.remove(j);
					j--;
				}
			}
		}
		return modifiedResults;
	}
	/**
	 * Handles the searching if the user desired to search for movies
	 * @param searchByYearOrTitle If the user searched by year or title
	 * @param searchedTitle The title the user searched
	 * @param yearsSearched The years the user is searching
	 * @param exactOrPartialMatch If the user wants an exact or partial match
	 * @param movieDatabase The database which all movies are contained
	 * @return Returns an arrayList of type media in which the movies found are returned
	 */
	private static ArrayList<Media> userSearchedMovies(String searchByYearOrTitle, String searchedTitle,
			ArrayList<Integer> yearsSearched, String exactOrPartialMatch, MovieDatabase movieDatabase) { 
		ArrayList<Media> results = new ArrayList<Media>();
		//Handles title searching of movies
		if(searchByYearOrTitle.equals("t") || searchByYearOrTitle.equals("b")){
			//Handles if the user wants an exact or partial match
			if(exactOrPartialMatch.equals("p")){
				//do partial searching
				//System.out.println("getting results before partial call: " + results.size());
				results.addAll(movieDatabase.searchPartialMovieTitles(searchedTitle));
				//System.out.println("getting results after partial  call: " + results.size());
			}
			if(exactOrPartialMatch.equals("e")){
				//do exact matching
				results.addAll(movieDatabase.searchExactMovieTitles(searchedTitle));
			}
		}
		if(searchByYearOrTitle.equals("y") || searchByYearOrTitle.equals("b")){
			if(yearsSearched.size() > 0){
				results.addAll(movieDatabase.searchMovieYears(yearsSearched));
			}
		}
		return results;
	}

	/**
	 * Called when the user wishes to search a Series
	 * @param episodeTitleIncluded Whether or not the episode titles are included in the search
	 * @param searchByYearOrTitle If the user wants to search by year or title
	 * @param searchedTitle Given title to search
	 * @param searchedYear Given year to search
	 * @param exactOrPartialMatch If the user wants exact or partial matches
	 * @param seriesDatabase The database to be used in searching
	 * @return returns a string of outputs.
	 */
	private static ArrayList<Media> userSearchedSeries(String episodeTitleIncluded, String searchByYearOrTitle,
			String searchedTitle, ArrayList<Integer> searchedYears, String exactOrPartialMatch, SeriesDatabase seriesDatabase) {
		ArrayList<Media> resultsList = new ArrayList<Media>();
		
		//Implying that exact or partial exists implies that title was prompted
		if(exactOrPartialMatch.equals("e")){
			
			//Adds every found title in each parent series
			resultsList.addAll(seriesDatabase.searchExactMediaTitleMatches(searchedTitle, 
					seriesDatabase.getSeriesListAsMedia()));
			
			//If the user wishes to find results of episodes, adds the episodes that match the title to the results arraylist
			if(episodeTitleIncluded.equals("y")){
				resultsList.addAll(seriesDatabase.searchExactMediaTitleMatches(searchedTitle, seriesDatabase.getAllEpisodesAsMedia()));
			}
		}
		else if(exactOrPartialMatch.equals("p")){
			//Adds every found title that contains the given phrase in each parent series
			resultsList.addAll(SeriesDatabase.searchPartialMediaTitleMatches(searchedTitle, seriesDatabase.getSeriesListAsMedia()));
			
			//If episodes are desired, searches for all partial episode titles
			if(episodeTitleIncluded.equals("y")){
				resultsList.addAll(SeriesDatabase.searchPartialMediaTitleMatches(searchedTitle, seriesDatabase.getAllEpisodesAsMedia()));
			}
		}
		//If a range of years is given
		if(searchedYears.size() > 0){
			resultsList.addAll(seriesDatabase.searchMediaYears(searchedYears));
		}
		return resultsList;
	}
	private static ArrayList<Integer> parseUserYears(String years){
		String[] stringYearsArray = new String[10];
		ArrayList<Integer> correctYearsArray = new ArrayList<Integer>();
		
		if(years.contains(",")){
			stringYearsArray = years.split(",");
			for(String s : stringYearsArray){
				correctYearsArray.add(Integer.parseInt(s.trim()));
			}
			return correctYearsArray;
		}
		
		if(years.contains("-")){
			//Splits the year range into an array and casts them to two variables
			stringYearsArray = years.split("-");
			int yearStart = Integer.parseInt(stringYearsArray[0].trim());
			int yearEnd = Integer.parseInt(stringYearsArray[1].trim());
			
			//Adds all of the values between and including the start and end years given to the array of integers to
			//search for.
			while(yearStart <= yearEnd){
				correctYearsArray.add(yearStart);
				yearStart++;
			}
			return correctYearsArray;
		}
		return correctYearsArray;
	}
	/**
	 * Displays the results to the user and also returns a string to be savd
	 * @param results the final arraylist of searched results
	 * @param mediaTypeSearched What media the user searched
	 * @param exactOrPartialMatch If the user did an exact or partial match
	 * @param searchedTitleOrYear If the user searched for the title or year or both
	 * @param sortedByYearOrTitle How the user sorted
	 * @param episodeTitlesIncluded If episodes were included
	 * @param searchedYears The years that were searched
	 * @param searchedTitle The phrase that was searched
	 * @return Returns a string in the display format
	 */
	public static String displayTheResultsAsText(ArrayList<Media> results, String mediaTypeSearched, String exactOrPartialMatch, String searchedTitleOrYear,
			String sortedByYearOrTitle, String episodeTitlesIncluded, String searchedYears, String searchedTitle){
		String display = "";
		display = display + "SEARCHED ";
		if (mediaTypeSearched.equals("m")) {
			display = display + "MOVIES";
		}
		else if(mediaTypeSearched.equals("s")){
			display = display + "TV SERIES";
		}
		else if(mediaTypeSearched.equals("b")){
			display = display + "MOVIES, TV SERIES";
		}
		
		if(episodeTitlesIncluded.equals("y")){
			display = display + " AND EPISODES";
		}
		display = display + "\n";
		if(searchedTitleOrYear.equals("t") || searchedTitleOrYear.equals("b")){
			if(exactOrPartialMatch.equals("e")){
				display = display + "EXACT TITLE: " + searchedTitle;
				display = display + "\n";
			}else{
				display = display + "PARTIAL TITLE: " + searchedTitle;
				display = display + "\n";
			}
			
			if(searchedTitleOrYear.equals("b")){
				display = display + "YEARS: " + searchedYears; 
			}
			else{
				display = display + "YEARS: Any";
			}
		}else{ //The user searched a year
			display = display + "TITLE: Any" + "\n";
			display = display + "YEARS: " + searchedYears;
		}
		display = display + "\n";
		if(sortedByYearOrTitle.equals("y")){
			display = display + "SORTED BY YEAR" + "\n";
		}else{
			display = display + "SORTED BY TITLE" + "\n";
		}
		for(int i = 0; i <80; i++){
			display = display + "=";
		}
		display = display + "\n";
		for(Media m : results){
			display = display + m.toString() + "\n";
		}
		if(results.size() == 0){
			display = display + "No results found, try searching again.";
		}
		
		
		//Creates a scrollable UI of the users display data
		System.out.println("Invoking UI generation. May be behind the IDE. Also generating below after UI close.");
		JTextArea displayText = new JTextArea(display);
		JScrollPane displayListScrollable = new JScrollPane(displayText);
		displayListScrollable.setPreferredSize(new Dimension (580, 600));
		JOptionPane.showMessageDialog(null, displayListScrollable);
		//System.out.println(display);
		return display;
	}
}
