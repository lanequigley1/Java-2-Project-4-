package mediaTypes;
/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Contains methods responsible for constructing and manipulating Episodes.
 * </P>
 * @version 1.0
 * 
 */
public class Episode extends Media{
	/**
	 * The stored serial version UID
	 */
	private static final long serialVersionUID = -7965084237379072126L;
	/**Stores the suspension status of the episode**/
	private boolean isSuspended;
	/**Stores the name of the series this episode belongs to**/
	private String seriesBelongTo;
	/**
	 * Episode Constructor
	 * @param episodeTitle Title
	 * @param year Year
	 * @param isSuspended Suspension Status
	 * @param seriesBelongTo What series the episode is in
	 * @param formattedLine Original line
	 */
	public Episode(String episodeTitle, int year, boolean isSuspended, String seriesBelongTo, String formattedLine){
		this.seriesBelongTo = seriesBelongTo;
		this.title = episodeTitle;
		this.year = year;
		this.isSuspended = isSuspended;
		this.fileFormattedName = formattedLine;
	}
	/**
	 * Constructor for a default movie object
	 */
	public Episode(){
		this.seriesBelongTo =  "DEFAULT SERIES";
		this.title = "DEFAULT TITLE";
		this.year = 1;
		this.isSuspended = false;
	}
	/**
	 * Allows the user to get the name of the Series.
	 * @return Returns series name
	 */
	public String getSeriesName(){
		return this.title;
	}
	/**
	 * Allows the user to retrieve the status of the Episode
	 * @return returns the boolean status
	 */
	public Boolean getSuspendedStatus(){
		return this.isSuspended;
	}
	/**
	 * Allows the user to print an individual episode 
	 * @return Returns a string containing the movie information in a designated format
	 */
	@Override
	public String toString(){
		//"EPISODE: " + seriesTitle: + episodeTitle: + (episodeYear)
		String titleModify = this.title;
		String yearModify = Integer.toString(this.year);
		
		if(this.year < 0){
			yearModify = "UNSPECIFIED";
		}
		
		return "EPISODE: " + this.seriesBelongTo + ": " + titleModify + " (" + yearModify + ")" ;
	}
	/**
	 * Allows the user to compare two episodes. Inherited implementation from Media
	 */
	@Override
	public int compareTo(Object o) {
		return this.toString().compareTo(o.toString());
	}

}
