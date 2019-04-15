package people;
import java.util.ArrayList;

import common.MediaComparator;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;

/**
 * Allows for the storage of data on a person who makes a form of media. Media can be in the form of Movie, Series,
 * or Episode objects. The person can direct, produce, or act in the media.
 */
public class MediaMaker extends Media{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6601791419710396476L;
	/**The last name of the person**/
	private String lastName;
	/**The first name of the person**/
	private String firstName;
	/**A number that differentiates multiple names**/
	private String disambiguationNumeral;
	/**Arraylist of acting credits**/
	private ArrayList<Media> actingList;
	/**Arraylist of directing credits **/
	private ArrayList<Media> directingList;
	/**Arraylist of production credits**/
	private ArrayList<Media> producingList;
	
	/**
	 * The constructor for a MediaMaker object.
	 * @param lastName the last name of the media maker as a String
	 * @param firstName the first name of the media maker as a String
	 * @param disambiguationNumeral the disambiguation numeral contained in the file as a String
	 * @param actingList the ArrayList of Media the media maker has acted in
	 * @param directingList the ArrayList of Media the media maker has directed
	 * @param producingList the ArrayList of Media the media maker has produced
	 */
	public MediaMaker(String lastName, String firstName, String disambiguationNumeral, ArrayList<Media> actingList,
			ArrayList<Media> directingList, ArrayList<Media> producingList) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.disambiguationNumeral = disambiguationNumeral;
		this.actingList = actingList;
		this.directingList = directingList;
		this.producingList = producingList;
	}
	/**
	 * Default media maker constructor. Sets empty names and numerals.
	 */
	public MediaMaker() {
		this.lastName = "";
		this.firstName = "";
		this.disambiguationNumeral = "";
	}
	public ArrayList<Media> getActingList(){
		return this.actingList;
	}
	public ArrayList<Media> getDirectedList(){
		return this.directingList;
	}
	public ArrayList<Media> getProducedList(){
		return this.producingList;
	}
	/**
	 * Adds all acting credits from one list of type media to the internal media list for acting credits
	 * @param media Takes an arraylist of media
	 */
	public void addActingCredits(ArrayList<Media> media){
		this.actingList.addAll(media);
	}
	/**
	 * Adds all media credits from one list of type media to the internal media list for directing credits
	 * @param media Takes an arraylist of media
	 */
	public void addDirectingCredits(ArrayList<Media> media){
		this.directingList.addAll(media);
	}
	/**
	 * Adds all media credits from one list of type media to the internal media list for productions
	 * @param media Takes a media array
	 */
	public void addProducingCredits(ArrayList<Media> media){
		this.producingList.addAll(media);
	}
	/**
	 * Sets the last name of a person
	 * @param lastName Takes a string
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Sets the first name of the person
	 * @param firstName Takes a string
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getNumeralNumber(){
		return this.disambiguationNumeral;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getFirstName(){
		return this.firstName;
	}
	/**
	 * Returns the full name of the person in firstname lastname format
	 * @return Returns a string
	 */
	public String getFullName(){
		String result = firstName + " " + lastName;
		if(!this.disambiguationNumeral.equals("")){
			result += " " + this.disambiguationNumeral;
		}
		return result;
	}
	/**
	 * Returns the number of movie acted credits
	 * @return returns an int
	 */
	public int getMovieActingCredit(){
		int counter = 0;
		for(Media m : this.actingList){
			if(m instanceof Movie){
				counter++;
			}
		}
		return counter;
	}
	public int getSeriesActingCredit(){
		int counter = 0;
		for(Media m : this.actingList){
			if(m instanceof Series){
				counter++;
			}
		}
		return counter;
	}
	/**
	 * Returns the number of movie directed credits
	 * @return returns an int
	 */
	public int getMovieDirectedCredit(){
		int counter = 0;
		for(Media m : this.directingList){
			if(m instanceof Movie){
				counter++;
			}
		}
		return counter;
	}
	/**
	 * Returns the number of series directed credits
	 * @return Returns an int
	 */
	public int getSeriesDirectedCredit(){
		int counter = 0;
		for(Media m : this.directingList){
			if(m instanceof Series){
				counter++;
			}
		}
		return counter;
	}
	/**
	 * Returns the number of movie produced credits
	 * @return returns an int
	 */
	public int getMovieProducingCredit(){
		int counter = 0;
		for(Media m : this.producingList){
			if(m instanceof Movie){
				counter++;
			}
		}
		return counter;
	}
	/**
	 * Returns the number of series produced credits
	 * @return Returns an int
	 */
	public int getSeriesProducingCredit(){
		int counter = 0;
		for(Media m : this.producingList){
			if(m instanceof Series){
				counter++;
			}
		}
		return counter;
	}
	/**
	 * Overrides toString to return one large string of the given output for a person's movies or series
	 */
	@Override
	public String toString() {
		String result = getFullName();
		if(this.actingList.size() > 0){
			result += "\nACTING";
			this.actingList.sort(new MediaComparator(0));
			for(Media m : this.actingList){
				result += "\n" + m.toString();
			}
		}
		if(this.directingList.size() > 0){
			result += "\nDIRECTING";
			this.directingList.sort(new MediaComparator(0));
			for(Media m : this.directingList){
				result += "\n" + m.toString();
			}
		}
		if(this.producingList.size() > 0){
			result += "\nPRODUCING";
			this.producingList.sort(new MediaComparator(0));
			for(Media m : this.producingList){
				result += "\n" + m.toString();
			}
		}
		return result;
	}
	/**
	 * Finds the lowest year the person took part in
	 * @return Returns the year
	 */
	public int getLowestYear() {
		int n = 99999;
		ArrayList<Media> mergedList = new ArrayList<Media>(700);
		mergedList.addAll(actingList);
		mergedList.addAll(producingList);
		mergedList.addAll(directingList);
		for(Media m : mergedList){
			if(m.getStartYear() < n && m.getStartYear() != -1){
				n = m.getStartYear();
			}
		}
		return n;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}