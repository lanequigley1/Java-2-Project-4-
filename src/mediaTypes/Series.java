package mediaTypes;

import java.util.ArrayList;

/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Responsible for creating and manipulating Series objects.
 * </P>
 * @version 1.0
 * 
 */
public class Series extends Media{
	/**
	 * The stored serialVersionUID
	 */
	private static final long serialVersionUID = 4296779700912103083L;
	/**Stores whether or not the series was suspended**/
	private boolean isSuspended;
	/**Stores the start year**/
	private int yearStart;
	/**Stores the end of the series**/
	private int yearEnd;
	/**Stores all episodes in the series**/
	private ArrayList<Episode> episodesInSeries;
	/**
	 * Constructs a series object
	 * @param seriesTitle Title
	 * @param yearStart Start of the series
	 * @param yearEnd End of the series 
	 * @param formattedLine Origianl line
	 */
	public Series(String seriesTitle, int yearStart, int yearEnd, String formattedLine){
		this.title = seriesTitle;
		this.yearStart = yearStart;
		this.yearEnd = yearEnd;
		this.fileFormattedName = formattedLine;
		episodesInSeries = new ArrayList<Episode>();
	}
	/**
	 * Default constructor series
	 */
	public Series(){
		this.title = "defaultTitle";
		this.yearStart = 1;
		this.yearEnd = 1;
		episodesInSeries = new ArrayList<Episode>();
	}
	/**
	 * Allows the user to retrieve the status of the Series
	 * @return Returns the suspension state
	 */
	public boolean getSuspendedStatus(){
		return this.isSuspended;
	}
	/**
	 * Allows the user to get the end year of the series
	 * @return returns the year the series ended
	 */
	public int getEndYear(){
		return this.yearEnd;
	}
	/**
	 * Returns the start year of the series.
	 */
	@Override
	public int getStartYear(){
		return this.yearStart;
	}
	/**
	 * Allows the user to add new episodes to the series list
	 * @param e takes a movie
	 */
	public void add(Episode e){
		episodesInSeries.add(e);
	}
	/**
	 * Allows the user to retrieve the arraylist of episode
	 * @return Returns episodes array
	 */
	public ArrayList<Episode> getAllEpisodesInSeries(){
		//TESTED: Returns the array with the proper episodes
		return episodesInSeries;
	}
	/**
	 * Allows the user to print all episodes contained in the Series
	 * @return Returns a string of all episodes in a series list
	 */
	public String printEpisodes(){
		String movieList = "";
		for(Episode e : episodesInSeries){
			movieList = movieList + "\n" +  e.toString();
		}
		return movieList;
	}
	
	/**
	 * Returns the number of elements in the Series List
	 * @return returns and int of the array size
	 */
	public int size(){
		return episodesInSeries.size();
	}
	
	public void deleteEpisode(String title) {
		for (Episode e : episodesInSeries) {
			if (e.getTitle().equals(title)) {
				episodesInSeries.remove(e);
				return;
			}
		}
	}
	
	public void setEpisode(String oldTitle, Episode episode) {
		for (Episode e : episodesInSeries) {
			if (e.getTitle().equals(oldTitle)) {
				e = episode;
				return;
			}
		}
	}
	
	/**
	 * Allows the user to print the Series in a given format
	 * @return returns the series in a desired format
	 */
	@Override
	public String toString(){
		//"SERIES" + : "SeriesTitle" + "(yearRange)"
		String modifiedEndYear = Integer.toString(this.yearEnd);
		String modifiedStartYear = Integer.toString(this.yearStart);
		
		//Checks if no years are provided
		if(this.yearEnd < 0){
			modifiedEndYear = "UNSPECIFIED";
		}
		if(this.yearStart < 0){
			modifiedStartYear = "UNSPECIFIED";
		}
		
		return "SERIES: " + this.title + " ("+ modifiedStartYear + "-" + modifiedEndYear + ")";
	}
	
	
	/**
	 * Allows the user to compare two Series together
	 */
	@Override
	public int compareTo(Object o) {
		return this.toString().compareTo(o.toString());
	}

}
