package mediaTypes;
/**
 * Project #4
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Responsible for constructing and providing methods for manipulating movie objects.
 * </P>
 * @version 1.0
 * 
 */
public class Movie extends Media{
	/**
	 * The stored serialVersionUID
	 */
	private static final long serialVersionUID = -467182789870604533L;
	/**Stores the media type of the Movie**/
	private String releaseMedia;
	/**Stores the roman numeral representation of the Movie**/
	private String numeralRelease;
	private boolean isSuspended;
	
	/**
	 * Constructs a new movie
	 * @param title Takes the title of the Movie
	 * @param releaseMedia Takes the media type of the Movie
	 * @param numeralRelease Takes the numeral representation of the Movie 
	 * @param year Takes the year the movie was released
	 * @param formattedLine fileFriendly line
	 */
	public Movie(String title, String releaseMedia, String numeralRelease, int year, String formattedLine){
		this.title = title; //this.title references the inherited title from media.
		this.year = year; //this.year references the inherited year from media
		this.releaseMedia = releaseMedia;
		this.numeralRelease = numeralRelease;
		this.isSuspended = false;
		this.fileFormattedName = formattedLine;
	}
	/**
	 *
	 * Constructs a new movie
	 * @param title Takes the title of the Movie
	 * @param releaseMedia Takes the media type of the Movie
	 * @param numeralRelease Takes the numeral representation of the Movie 
	 * @param year Takes the year the movie was released
	 * @param isSuspended If the movie is suspended
	 * @param formattedLine File firnedly line
	 */
	public Movie(String title, String releaseMedia, String numeralRelease, int year, boolean isSuspended, String formattedLine){
		this.title = title; //this.title references the inherited title from media.
		this.year = year; //this.year references the inherited year from media
		this.releaseMedia = releaseMedia;
		this.numeralRelease = numeralRelease;
		this.isSuspended = isSuspended;
		this.fileFormattedName = formattedLine;
	}
	public Movie(String title, String releaseMedia, String numeralRelease, int year, boolean isSuspended){
		this.title = title; //this.title references the inherited title from media.
		this.year = year; //this.year references the inherited year from media
		this.releaseMedia = releaseMedia;
		this.numeralRelease = numeralRelease;
		this.isSuspended = isSuspended;
	}
	/**
	 * Default constructor
	 */
	public Movie(){
		this.title = "default";
		this.year = 1;
		this.releaseMedia = "defaultMedia";
		this.numeralRelease = "defaultNumerals";
	}
	/**
	 * Allows the user to retreive the media type of the Movie
	 * @return Returns the media type
	 */
	public String getReleaseMedia(){
		return this.releaseMedia;
	}
	/**
	 * Allows the user to retrieve the numeral release of the Movie
	 * @return Returns the numeral release of the Movie
	 */
	public String getNumeralRelease(){
		return this.numeralRelease;
	}
	public boolean getIsSuspended(){
		return this.isSuspended;
	}
	/**
	 * Allows the user to set the Media type of the Movie
	 * @param newReleaseMedia Takes the Media Type of the Movie
	 */
	public void setReleaseMedia(String newReleaseMedia){
		this.releaseMedia = newReleaseMedia;
	}
	/**
	 * Allows the user to set the numeral representation of a Movie
	 * @param newNumeralRelease Takes the string representation of the numeral notation
	 */
	public void setNumeralRelease(String newNumeralRelease){
		this.numeralRelease = newNumeralRelease;
	}
	/**
	 * Allows the user to print a Movie object in a given format
	 * @return Returns the display string
	 */
	public String displayString(){
		String modifiedYear = Integer.toString(this.year);
		//Checks if a year is provided
		if(this.year < 0){
			modifiedYear = "UNSPECIFIED";
		}
		return "MOVIE" + this.releaseMedia +": " + this.title + " ("+  modifiedYear + this.numeralRelease + ")";
	}
	@Override 
	public String toString(){
		String modifiedYear = Integer.toString(this.year);
		String modifiedNumeral = this.numeralRelease;
		if(!modifiedNumeral.isEmpty()){
			modifiedNumeral = "/" + modifiedNumeral;
		}
		if(this.year < 0){
			modifiedYear = "UNSPECIFIED";
		}
		return "MOVIE" + this.releaseMedia +": " + this.title + " ("+  modifiedYear + modifiedNumeral + ")";
	}
	
	/**
	 * Allows the user to compare two Movies together
	 */
	@Override
	public int compareTo(Object o) {
		return this.toString().compareTo(o.toString());
	}
	
}
