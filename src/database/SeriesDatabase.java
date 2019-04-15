package database;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import mediaTypes.Episode;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;

/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * This class creates a data base of all Series and provides methods for the lists manipulation.
 * </P>
 * @version 1.0
 * 
 */
public class SeriesDatabase {
	/**Creates a new ArrayList of type Series**/
	private ArrayList<Series>SeriesList;
	/**
	 * Constructs and abstracts a database of Series by using the SeriesList
	 */
	public SeriesDatabase(){
		SeriesList = new ArrayList<Series>();
	}
	public ArrayList<Series> toArrayList(){
		ArrayList<Series> seriesList = new ArrayList<Series>(SeriesList.size());
		for(Series s : SeriesList){
			seriesList.add(s);
		}
		return seriesList;
	}
	
	/**
	 * Adds series to the series database
	 * @param newSeries Takes a new series for adding
	 */
	public void addSeries(Series newSeries){
		//Tested: Series are added properly
		SeriesList.add(newSeries);
	}
	public static String formatFriendlyText(ArrayList<Series> finalModifiedResults) {
		String display = "";
		for(Series s : finalModifiedResults){
			display += s.getFileFormattedName() + "\n";
		}
		return display;
	}
	/**
	 * Adds episodes to a series
	 * @param index Index at which the episode needs to be
	 * @param episode Episode object to be added
	 */
	public void addEpisodeToSeries(int index, Episode episode){
		if(index >= 0 && index < SeriesList.size()){
			SeriesList.get(index).add(episode);
		}
	}
	/**
	 * Allows the user to get the size of the database
	 * @return returns the size of database
	 */
	public int size(){
		return SeriesList.size();
	}
	/**
	 * Sorts the database using Collections.sort
	 */
	public void sortInternalList(){
		Collections.sort(SeriesList);
	}
	/**
	 * Finds if a Series exists with a given title
	 * @param title Title of series
	 * @return Returns the index that the series was found or -1 if not found.
	 */
	public int findExistingSeriesByTitle(String title){
		int counter = 0;
		for(Series s : SeriesList){
			if(s.getTitle().equals(title)){
				return counter;
			}
			counter++;
		}
		return -1;
	}
	
	/**
	 * Allows the user to retrieve all series titles in the series database
	 * @return returns a string array of all series titles contained in the Series Database
	 */
	public String[] getAllSeriesTitles(){
		//TESTED: Returns the titles properly
		String[] seriesTitles = new String[SeriesList.size()];
		int counter = 0;
		for(Series s : SeriesList){
			seriesTitles[counter] = s.getTitle();
			counter++;
		}
		return seriesTitles;
	}
	/**
	 * Allows the user to retrieve an array of every series title in a given arraylist
	 * @param listOfSeries Takes a list of series to be searched
	 * @return Returns a string array of every series title.
	 */
	public static String[] getAllMediaTitles(ArrayList<Media> listOfSeries ){
		//TESTED: Returns the titles of SERIES properly. 
		String[] copyListTitles = new String[listOfSeries.size()];
		int counter = 0;
		for(Media s : listOfSeries){
			copyListTitles[counter] = s.getTitle();
			counter++;
		}
		return copyListTitles;
	}
	/**
	 * Searches the parent series for an exact match of a given string
	 * @param title Takes the desired search title
	 * @param source Takes a source array for title searching
	 * @return returns an arraylist of every series whose title was an exact match.
	 */
	public ArrayList<Media> searchExactMediaTitleMatches(String title, ArrayList<Media> source){
		
		ArrayList<Media> copyList = new ArrayList<Media>(source);
		String[] copyListTitles = new String[source.size()];
		ArrayList<Media> results = new ArrayList<Media>();
		//is set to a new array that contains all titles of series
		copyListTitles = getAllMediaTitles(copyList);
		//Sorts the array in case the user is bad.
		Collections.sort(source);
		//Finds an index in which title is an exact match
		while(Arrays.binarySearch(copyListTitles, title) > 0){
			//Stores the index found
			int indexFound = Arrays.binarySearch(copyListTitles, title);
			//Adds the series to the results arrayList
			results.add(copyList.get(indexFound));
			//Removes the found Series from the list
			copyList.remove(indexFound);
			//Constructs a new string array and repeats the loop
			copyListTitles = getAllMediaTitles(copyList);
		}
		return results;
	}
	/**
	 * Allows the user to get the reference to the internal SeriesList
	 * @return returns the internal series ArrayList
	 */
	public ArrayList<Series> getSeriesList(){
		return SeriesList;
	}
	/**
	 * Allows the user to get the series list as a media type
	 * @return returns an arrayList of type media from the converted internal series list
	 */
	public ArrayList<Media> getSeriesListAsMedia(){
		ArrayList<Media> newSeriesList = new ArrayList<Media>(SeriesList);
		return newSeriesList;
	}
	/**
	 * Allows the user to get an arraylist of all episodes in every series
	 * @return returns an arraylist of episodes
	 */
	public ArrayList<Episode> getAllEpisodes(){
		ArrayList<Episode> results = new ArrayList<Episode>();
		for(Series s : SeriesList){
			results.addAll(s.getAllEpisodesInSeries());
		}
		return results;
	}
	/**
	 * Allows the user to get all episodes from the series list as a media array
	 * @return returns an arraylist of all the episodes in media type
	 */
	public ArrayList<Media> getAllEpisodesAsMedia(){
		ArrayList<Media> results = new ArrayList<Media>();
		for(Series s : SeriesList){
			results.addAll(s.getAllEpisodesInSeries());
		}
		return results;
	}
	/**
	 * Allows the user to get all partial title matches of the series
	 * @param title The title desired to search
	 * @param source the source arraylist
	 * @return returns an arraylist of type media for all partial media matches
	 */
	public static ArrayList<Media> searchPartialMediaTitleMatches(String title,
			ArrayList<Media> source) {
		ArrayList<Media> results = new ArrayList<Media>();
		for(Media m : source){
			if(m.getTitle().contains(title)){
				results.add(m);
			}
		}
		return results;
	}
	/**
	 * Allows the user to search the series list for all series that match the given year ranne
	 * @param yearRange Takes an arraylist of every year matched
	 * @return Returns an arraylist of type media for all matched series
	 */
	public ArrayList<Media> searchMediaYears(ArrayList<Integer> yearRange) {
		ArrayList<Media> results = new ArrayList<Media>();
		for(Integer i : yearRange){
			for(Series s : SeriesList){
				if(s.getStartYear() == i){
					results.add(s);
				}
			}
		}
		return results;
	}
	
	public void addAllSeries(ArrayList<Series> seriesList) {
		SeriesList.addAll(seriesList);
	}

	public void editSeries(int index, Series s) {
		SeriesList.set(index, s);
	}

	public void deleteSeries(int index) {
		SeriesList.remove(index);
	}
	
	public void editEpisode(int index, String oldEpisodeTitle, Episode myNewEpisode) {
		SeriesList.get(index).setEpisode(oldEpisodeTitle, myNewEpisode);
	}

	public void deleteEpisode(int index, String oldEpisodeTitle) {
		SeriesList.get(index).deleteEpisode(oldEpisodeTitle);
	}
	
}
