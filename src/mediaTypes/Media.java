package mediaTypes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Creates an abstract class used for differing media types parsed by the program.
 * </P>
 */
public abstract class Media implements Comparable<Object>, Serializable{
	/**
	 * The generated serial version UID
	 */
	private static final long serialVersionUID = 7928590778331295433L;
	/**Stores the Title in a String**/
	protected String title;
	/**Stores the year in an Int**/
	protected int year;
	/**Stores the string that the object was parsed from**/
	protected String fileFormattedName;
	
	/**
	 * Allows for the user to retrieve the title
	 * @return Returns the title in a String
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * Allows the user to retreieve the year
	 * @return Returns the year as an int
	 */
	public int getStartYear(){
		return this.year;
	}
	/**
	 * Iterates through a list of media for any year matches
	 * @param year Given year to search
	 * @param list List to iterate through
	 * @return Returns the number of years found
	 */
	public static int getMediasInYear(int year, ArrayList<Media> list) {
		int n = 0;
		for(Media m : list){
			if(year == m.getStartYear()){
				n++;
			}
		}
		return n;
	}
	/**
	 * Gets the format friendly name of the object
	 * @return Returns string
	 */
	public String getFileFormattedName(){
		return this.fileFormattedName;
	}
	/**
	 * Sets the file friendly format name
	 * @param line Takes the friendly line
	 */
	public void setFileFormattedName(String line){
		this.fileFormattedName = line;
	}
}
