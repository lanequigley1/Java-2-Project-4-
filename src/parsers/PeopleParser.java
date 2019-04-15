package parsers;

import java.util.ArrayList;
import database.MediaMakerDatabase;
import database.MovieDatabase;
import database.SeriesDatabase;
import mediaTypes.Episode;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;
import people.MediaMaker;

/**
 * This class is designed for parsing files. It has the ability to parse Movie, Series, Episode, and MediaMaker
 * objects from ArrayList objects of type String, provided the String objects are in the correct format.
 */
public class PeopleParser {
	public static final int ACTING_FILE = 0;
	public static final int DIRECTING_FILE = 1;
	public static final int PRODUCING_FILE = 2;
	
	/**
	 * Parses movies
	 * @param line String
	 * @return Movie
	 */
	private static Movie parseMovieCredit(String line) {
		String formatFriendlyLine = line;
		String title;
		int year = -1;
		String releaseFormat = "";
		String releaseNumeral = "";
		boolean isSuspended = false;
		
		int beginIndex = 0;
		int endIndex = line.indexOf('(');
		
		title = line.substring(beginIndex, endIndex).trim();
		
		line = line.substring(endIndex); // Remove title from beginning of line
		
		beginIndex = line.indexOf('(') + 1;
		endIndex = beginIndex + 4;
		
		try {
			year = Integer.parseInt(line.substring(beginIndex, endIndex));
		} catch (NumberFormatException e) {
			// System.out.println(e);
		}
		
		line = line.substring(endIndex); // Remove year from beginning of line
		
		if (line.charAt(0) == '/') {
			releaseNumeral = line.substring(1, line.indexOf(')'));
		}
		
		if (line.contains("(TV)")) {
			releaseFormat = " (TV)";
		} else if (line.contains("(V)")) {
			releaseFormat = " (V)";
		}
		
		if (line.contains("{{SUSPENDED}}")) {
			isSuspended = true;
		}
		
		return new Movie(title, releaseFormat, releaseNumeral, year, isSuspended, formatFriendlyLine);
	}
	
	/**
	 * Parses an Episode object.
	 * @param line the String object from which to parse the Episode data
	 * @return an Episode object containing the data from the String
	 */
	private static Episode parseEpisodeCredit(String line) {
		String formatFriendlyLine = line;
		String parentSeriesTitle;
		String title = "";
		int year = -1;
		boolean isSuspended = false;
		
		int beginIndex = 1;
		int endIndex = 1;
		
		while (line.charAt(endIndex) != '"') {
			endIndex += 1;
		}
		
		parentSeriesTitle = line.substring(beginIndex, endIndex);
		
		line = line.substring(endIndex); // Remove title from beginning of line

		beginIndex = line.indexOf('(') + 1;
		endIndex = beginIndex + 4;
		
		try {
			year = Integer.parseInt(line.substring(beginIndex, endIndex));
		} catch (NumberFormatException e) {
			// System.out.println(e);
		}
		
		line = line.substring(endIndex); // Remove year from beginning of line
		
		beginIndex = line.indexOf('{') + 1;
		endIndex = beginIndex;
		
		if (beginIndex > 0) {
			if (line.charAt(beginIndex) == '(') { // No title is given, only episode number or date
				while (line.charAt(endIndex) != ')') {
					endIndex += 1;
				}
				endIndex += 1; // To include closing parenthesis
			} else { // Title is given
				while (line.charAt(endIndex) != '}' && line.charAt(endIndex) != '(') {
					endIndex += 1;
				}
			}
			
			title = line.substring(beginIndex, endIndex).trim();
		}
		
		if (line.contains("{{SUSPENDED}}")) {
			isSuspended = true;
		}
		
		return new Episode(title, year, isSuspended, parentSeriesTitle, formatFriendlyLine);
	}
	
	
	/**
	 * Parses the people
	 * @param lines Arraylist of lines for people to parse
	 * @param fileType Type of file
	 * @param mmDatabase Database of people
	 * @param fileMovieDatabase Database of movies
	 * @param fileSeriesDatabase Database of series
	 */
	public static void parseMediaMakers(ArrayList<String> lines, int fileType, MediaMakerDatabase mmDatabase, MovieDatabase fileMovieDatabase, SeriesDatabase fileSeriesDatabase) {
		
		int i = 0;
		while (i < lines.size()) {
			
			String line = lines.get(i);
			
			MediaMaker mediaMaker;
			String lastName = "";
			String firstName = "";
			String disambiguationNumeral = "";
			ArrayList<Media> actingList = new ArrayList<Media>();
			ArrayList<Media> directingList = new ArrayList<Media>();
			ArrayList<Media> producingList = new ArrayList<Media>();
			
				
			int beginIndex = 0;
			int endIndex = line.indexOf(',');
			
			if (beginIndex >= 0 && endIndex >=0)
				lastName = line.substring(beginIndex, endIndex);
			
			
			beginIndex = endIndex + 2;
			while (line.charAt(endIndex) != '(' && line.charAt(endIndex) != '\t') {
				endIndex += 1;
			}
			
			if (beginIndex >= 0 && endIndex >= 0)
				firstName = line.substring(beginIndex, endIndex);
			
			beginIndex = endIndex;
			if (line.charAt(beginIndex) == '(') {
				while (line.charAt(endIndex) != ')') {
					endIndex += 1;
				} 
				if (beginIndex >= 0 && endIndex >= 0)
					disambiguationNumeral = line.substring(beginIndex+1, endIndex);
			}
			
			if (endIndex >= 0)
				line = line.substring(beginIndex);
			
			// Add media credits
			while (i < lines.size() && !line.isEmpty() && !Character.isAlphabetic(line.charAt(0))) {
				
				if (line.charAt(0) != '"') {
					Movie parsedMovie = parseMovieCredit(line);
					if(fileType == ACTING_FILE){
						actingList.add(parsedMovie); // Parse Movie
					}else if(fileType == DIRECTING_FILE){
						directingList.add(parsedMovie);
					}else if(fileType == PRODUCING_FILE){
						producingList.add(parsedMovie);
					}
					fileMovieDatabase.addMovie(parsedMovie);
				} else {
					Episode parsedEpisode = parseEpisodeCredit(line);
					int index = fileSeriesDatabase.findExistingSeriesByTitle(parsedEpisode.getTitle());
					if(index >= 0){
						fileSeriesDatabase.addEpisodeToSeries(index, parsedEpisode);
					}else{
						//Creates a new series with the title if the series was not found in the database
						Series newSeries = new Series(parsedEpisode.getSeriesName(), -1, -1, "");
						newSeries.add(parsedEpisode);
						fileSeriesDatabase.addSeries(newSeries);
					}
					if(fileType == ACTING_FILE){
						actingList.add(parseEpisodeCredit(line)); // Parse Episode
					}else if(fileType == DIRECTING_FILE){
						directingList.add(parseEpisodeCredit(line));
					}else if(fileType == PRODUCING_FILE){
						producingList.add(parseEpisodeCredit(line));
					}
				}
				
				i += 1;
				
				if (i < lines.size())
					line = lines.get(i);
			}
			mediaMaker = new MediaMaker(lastName, firstName, disambiguationNumeral, 
					actingList, directingList, producingList);
			
			if(mmDatabase.getMediaMakerMap().get(mediaMaker.getFullName()) != null){
				if(fileType == ACTING_FILE) {
					mmDatabase.getMediaMakerMap().get(mediaMaker.getFullName()).addActingCredits(actingList);
				}else if(fileType == DIRECTING_FILE) {
					mmDatabase.getMediaMakerMap().get(mediaMaker.getFullName()).addDirectingCredits(directingList);
				}else if(fileType == PRODUCING_FILE) {
					mmDatabase.getMediaMakerMap().get(mediaMaker.getFullName()).addProducingCredits(producingList);
				}
			}else{
				mmDatabase.getMediaMakerMap().put(mediaMaker.getFullName(), mediaMaker);
			}
			
			while (i < lines.size() && lines.get(i).isEmpty())
				i += 1; // Advance beyond blank line to next entry
			
		} // End outer while loop	
	}
}