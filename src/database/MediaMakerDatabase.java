package database;
import java.util.LinkedHashMap;

import mediaTypes.Episode;
import mediaTypes.Media;
import mediaTypes.Movie;
import parsers.PeopleParser;
import people.MediaMaker;

/**
 * Allows for the storage of MediaMaker objects using a LinkedHash Map. Also provides ways of searching through 
 * the data for names of media makers. 
 */
public class MediaMakerDatabase {
	/**Hashmap for all persons**/
	private LinkedHashMap<String, MediaMaker> mediaMakerMap;
	
	/**
	 * The constructor for a MediaMakerDatabase object.
	 */
	public MediaMakerDatabase() {
		mediaMakerMap = new LinkedHashMap<String, MediaMaker>();
	}
	
	public void put(MediaMaker mediaMaker) {
		mediaMakerMap.put(mediaMaker.getFullName(), mediaMaker);
	}
	
	/**
	 * Calls the put all Command on the internal database
	 * @param mediaMakers Persons
	 */
	public void putAll(LinkedHashMap<String, MediaMaker> mediaMakers) {
		mediaMakerMap.putAll(mediaMakers);
	}
	/**
	 * Gets the size of the map
	 * @return Returns size
	 */
	public int size(){
		return this.mediaMakerMap.size();
	}
	/**
	 * Gets the mediaMakerMap
	 * @return the LinkedHashMap of MediaMaker objects and their corresponding names
	 */
	public LinkedHashMap<String, MediaMaker> getMediaMakerMap() {
		return this.mediaMakerMap;
	}

	public void editPerson(String key, MediaMaker m) {
		mediaMakerMap.remove(key);
		mediaMakerMap.put(m.getFullName(), m);
	}

	public void deletePerson(String key) {
		mediaMakerMap.remove(key);
	}
	
	public static String formatTextFriendlyModel(int peopleType, LinkedHashMap<String, MediaMaker> peopleList) {
		
		String result = "";
		
		if (peopleType == PeopleParser.ACTING_FILE) {
			
			for (MediaMaker maker : peopleList.values()) {
				if (!maker.getActingList().isEmpty()) {
					result += maker.getLastName() + ", " + maker.getFirstName();
					for (Media credit : maker.getActingList()) {
						if (credit instanceof Movie) {
							Movie movie = (Movie) credit;
							result += "     " + movie.getTitle() + " (" + movie.getStartYear() 
								+ ") (" + movie.getReleaseMedia() + ")\n";
						} else if (credit instanceof Episode) {
							Episode episode = (Episode) credit;
							result += "     \"" + episode.getSeriesName() + "\" " + episode.getStartYear() + ") {" 
									+ episode.getTitle() + "}";
						}
					}
				}
			}
			result += "\n";
			
		} else if (peopleType == PeopleParser.DIRECTING_FILE) {
			
			for (MediaMaker maker : peopleList.values()) {
				if (!maker.getDirectedList().isEmpty()) {
					result += maker.getLastName() + ", " + maker.getFirstName();
					for (Media credit : maker.getActingList()) {
						if (credit instanceof Movie) {
							Movie movie = (Movie) credit;
							result += "     " + movie.getTitle() + " (" + movie.getStartYear() 
								+ ") (" + movie.getReleaseMedia() + ")\n";
						} else if (credit instanceof Episode) {
							Episode episode = (Episode) credit;
							result += "     \"" + episode.getSeriesName() + "\" " + episode.getStartYear() + ") {" 
									+ episode.getTitle() + "}\n";
						}
					}
				}
			}
			result += "\n";
			
		} else if (peopleType == PeopleParser.PRODUCING_FILE) {
			
			for (MediaMaker maker : peopleList.values()) {
				if (!maker.getProducedList().isEmpty()) {
					result += maker.getLastName() + ", " + maker.getFirstName();
					for (Media credit : maker.getActingList()) {
						if (credit instanceof Movie) {
							Movie movie = (Movie) credit;
							result += "     " + movie.getTitle() + " (" + movie.getStartYear() 
								+ ") (" + movie.getReleaseMedia() + ")\n";
						} else if (credit instanceof Episode) {
							Episode episode = (Episode) credit;
							result += "     \"" + episode.getSeriesName() + "\" " + episode.getStartYear() + ") {" 
									+ episode.getTitle() + "}\n";
						}
					}
				}
			}
			result += "\n";
			
		} else {
			System.out.println("Wrong argument");
		}
			
		System.out.println(result);
		return result;
		
	}
	
}
