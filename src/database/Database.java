package database;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import mediaTypes.Episode;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;
import people.MediaMaker;

public class Database {
	
	public MediaMakerDatabase makerDb;
	public MovieDatabase movieDb;
	public SeriesDatabase seriesDb;
	
	public Database(MediaMakerDatabase makerDb2, 
			MovieDatabase movieDb2, 
			SeriesDatabase seriesDb2) {
		
		makerDb = makerDb2;
		movieDb = movieDb2;
		seriesDb = seriesDb2;
		
	}
	public LinkedList<Movie> getMovieList(){
		return movieDb.getList();
	}
	/**
	 * Allows the object to call Collections.sort on its internal list
	 */
	public void movieSort(){
		movieDb.internalSort();
	}
	/**
	 * Allows the addition of movie objects to the movie list
	 * @param movie Takes a movie used for addition to the list
	 */
	public void addMovie(Movie movie){
		movieDb.addMovie(movie);
	}
	
	public void addAllMovies(ArrayList<Movie> movieList) {
		movieDb.addAllMovies(movieList);
	}
	
	/**
	 * Searches the internal movie list for all partial title matches
	 * @param title The fragment of title used in searching
	 * @return returns an arrayList of type media of all the partial title matches
	 */
	public ArrayList<Media> searchPartialMovieTitles(String title){
		return movieDb.searchPartialMovieTitles(title);
	}
	/**
	 * Allows the user to pull ALL titles of media/movie from a given arraylist
	 * @param titles The title fragment desired
	 * @return Returns an arrayList of every title from the given media arraylist
	 */
	public String[] getAllMovieTitles(ArrayList<Media> titles){
		return movieDb.getAllMovieTitles(titles);
	}
	/**
	 * Searches for the exact movie title
	 * @param searchedTitle The title fragment that is searched
	 * @return returns the arrayList of movies that met the exact movie match
	 */
	public ArrayList<Media> searchExactMovieTitles(String searchedTitle) {
		return movieDb.searchExactMovieTitles(searchedTitle);
	}
	/**
	 * Searched the movie list array for all year matches
	 * @param yearsSearched the years that are searched
	 * @return returns an arraylist of media for every movie that matched the list of given years
	 */
	public ArrayList<Media> searchMovieYears(ArrayList<Integer> yearsSearched) {
		return movieDb.searchMovieYears(yearsSearched);
	}
	/**
	 * Calls the put all Command on the internal database
	 * @param mediaMakers Persons
	 */
	public void putAll(LinkedHashMap<String, MediaMaker> mediaMakers) {
		makerDb.putAll(mediaMakers);
	}
	/**
	 * Gets the size of the map
	 * @return Returns size
	 */
	public int mediaMakerSize(){
		return makerDb.size();
	}
	/**
	 * Gets the mediaMakerMap
	 * @return the LinkedHashMap of MediaMaker objects and their corresponding names
	 */
	public LinkedHashMap<String, MediaMaker> getMediaMakerMap() {
		return makerDb.getMediaMakerMap();
	}
	
	public void addSeries(Series newSeries){
		seriesDb.addSeries(newSeries);
	}
	/**
	 * Adds episodes to a series
	 * @param index Index at which the episode needs to be
	 * @param episode Episode object to be added
	 */
	public void addEpisodeToSeries(int index, Episode episode){
		seriesDb.addEpisodeToSeries(index, episode);
	}
	/**
	 * Allows the user to get the size of the database
	 * @return returns the size of database
	 */
	public int seriesSize(){
		return seriesDb.size();
	}
	/**
	 * Sorts the database using Collections.sort
	 */
	public void seriesSort(){
		seriesDb.sortInternalList();
	}
	/**
	 * Finds if a Series exists with a given title
	 * @param title Title of series
	 * @return Returns the index that the series was found or -1 if not found.
	 */
	public int findExistingSeriesByTitle(String title){
		return seriesDb.findExistingSeriesByTitle(title);
	}
	
	/**
	 * Allows the user to retrieve all series titles in the series database
	 * @return returns a string array of all series titles contained in the Series Database
	 */
	public String[] getAllSeriesTitles(){
		return seriesDb.getAllSeriesTitles();
	}
	/**
	 * Allows the user to retrieve an array of every series title in a given arraylist
	 * @param listOfSeries Takes a list of series to be searched
	 * @return Returns a string array of every series title.
	 */
	public String[] getAllMediaTitles(ArrayList<Media> listOfSeries ){
		return seriesDb.getAllMediaTitles(listOfSeries);
	}
	/**
	 * Searches the parent series for an exact match of a given string
	 * @param title Takes the desired search title
	 * @param source Takes a source array for title searching
	 * @return returns an arraylist of every series whose title was an exact match.
	 */
	public ArrayList<Media> searchExactMediaTitleMatches(String title, ArrayList<Media> source){
		return seriesDb.searchExactMediaTitleMatches(title, source);
	}
	/**
	 * Allows the user to get the reference to the internal SeriesList
	 * @return returns the internal series ArrayList
	 */
	public ArrayList<Series> getSeriesList(){
		return seriesDb.getSeriesList();
	}
	public ArrayList<MediaMaker> getAllPeople(){
		ArrayList<MediaMaker> mediaMakers = new ArrayList<MediaMaker>(100);
		for(MediaMaker m : makerDb.getMediaMakerMap().values()){
			mediaMakers.add(m);
		}
		return mediaMakers;
	}
	
	public ArrayList<MediaMaker> getAllActors(){
		ArrayList<MediaMaker> actors = new ArrayList<MediaMaker>(100);
		for(MediaMaker m : makerDb.getMediaMakerMap().values()){
			if(!m.getActingList().isEmpty()){
				actors.add(m);
			}
		}
		return actors;
	}
	
	public ArrayList<MediaMaker> getAllProducers(){
		ArrayList<MediaMaker> producers = new ArrayList<MediaMaker>(100);
		for(MediaMaker m : makerDb.getMediaMakerMap().values()){
			if(!m.getProducedList().isEmpty()){
				producers.add(m);
			}
		}
		return producers;
	}
	public ArrayList<MediaMaker> getAllDirectors(){
		ArrayList<MediaMaker> directors = new ArrayList<MediaMaker>(100);
		for(MediaMaker m : makerDb.getMediaMakerMap().values()){
			if(!m.getDirectedList().isEmpty()){
				directors.add(m);
			}
		}
		return directors;
	}
	/**
	 * Allows the user to get the series list as a media type
	 * @return returns an arrayList of type media from the converted internal series list
	 */
	public ArrayList<Media> getSeriesListAsMedia(){
		return seriesDb.getSeriesListAsMedia();
	}
	/**
	 * Allows the user to get an arraylist of all episodes in every series
	 * @return returns an arraylist of episodes
	 */
	public ArrayList<Episode> getAllEpisodes(){
		return seriesDb.getAllEpisodes();
	}
	/**
	 * Allows the user to get all episodes from the series list as a media array
	 * @return returns an arraylist of all the episodes in media type
	 */
	public ArrayList<Media> getAllEpisodesAsMedia(){
		return seriesDb.getAllEpisodesAsMedia();
	}
	/**
	 * Allows the user to get all partial title matches of the series
	 * @param title The title desired to search
	 * @param source the source arraylist
	 * @return returns an arraylist of type media for all partial media matches
	 */
	public ArrayList<Media> searchPartialMediaTitleMatches(String title,
			ArrayList<Media> source) {
		return seriesDb.searchPartialMediaTitleMatches(title, source);
	}
	/**
	 * Allows the user to search the series list for all series that match the given year ranne
	 * @param yearRange Takes an arraylist of every year matched
	 * @return Returns an arraylist of type media for all matched series
	 */
	public ArrayList<Media> searchMediaYears(ArrayList<Integer> yearRange) {
		return seriesDb.searchMediaYears(yearRange);
	}
	
	public void addPerson(MediaMaker person) {
		makerDb.put(person);
	}
	
	public void clearMovies() {
		movieDb = new MovieDatabase();
	}
	
	public void clearSeries() {
		seriesDb = new SeriesDatabase();
	}
	
	public void clearPeople() {
		makerDb = new MediaMakerDatabase();
	}
	
	public void clearAll() {
		makerDb = new MediaMakerDatabase();
		movieDb = new MovieDatabase();
		seriesDb = new SeriesDatabase();
	}

	public void addAllSeries(ArrayList<Series> seriesList) {
		seriesDb.addAllSeries(seriesList);
	}
	
	public void editMovie(int index, Movie m) {
		movieDb.editMovie(index, m);
	}

	public void editSeries(int index, Series s) {
		seriesDb.editSeries(index, s);
		
	}

	public void deleteMovie(int index) {
		movieDb.deleteMovie(index);
		
	}

	public void deleteSeries(int index) {
		seriesDb.deleteSeries(index);
		
	}

	public void editEpisode(String seriesTitle, String oldEpisodeTitle, Episode myNewEpisode) {
		int index = seriesDb.findExistingSeriesByTitle(seriesTitle);
		if (index > 0) {
			seriesDb.editEpisode(index, oldEpisodeTitle, myNewEpisode);
		}
		
	}

	public void deleteEpisode(String seriesTitle, String oldEpisodeTitle) {
		int index = seriesDb.findExistingSeriesByTitle(seriesTitle);
		if (index > 0) {
			seriesDb.deleteEpisode(index, oldEpisodeTitle);
		}
		
	}

	public void editPerson(String key, MediaMaker m) {
		makerDb.editPerson(key, m);
		
	}

	public void deletePerson(String key) {
		makerDb.deletePerson(key);
		
	}

	public void addEpisode(Episode episode) {
		int index = seriesDb.findExistingSeriesByTitle(episode.getSeriesName());
		if (index >= 0) {
			seriesDb.addEpisodeToSeries(index, episode);
		} else {
			Series series = new Series(episode.getSeriesName(), episode.getStartYear(), episode.getStartYear(), "");
			series.add(episode);
			seriesDb.addSeries(series);
		}
		
	}

}
