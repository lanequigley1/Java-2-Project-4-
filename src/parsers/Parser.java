package parsers;

import java.util.ArrayList;

import database.MovieDatabase;
import database.SeriesDatabase;
import mediaTypes.Episode;
import mediaTypes.Movie;
import mediaTypes.Series;
/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Parses the file into different media objects
 * </P>
 * @version 1.0
 * 
 */
public class Parser {
	/**
	 * Returns a database of Series
	 * @param fileLines Takes an arraylist of lines
	 * @return returns a new SeriesDatabase
	 */
	public static SeriesDatabase parseSeriesDatabase(ArrayList<String> fileLines){
		int counter = 0;
		SeriesDatabase fileSeriesDatabase = new SeriesDatabase();
		
		while (counter < fileLines.size()){
			//Checks if the current line is a parent series
			if(fileLines.get(counter).charAt(fileLines.get(counter).length() - 5) == '-'){
				//Constructs a new series and sets currentWorkingSeries to this place in memory
				Series currentWorkingSeries = parseSeries(fileLines.get(counter));
				//Increments to the next line
				counter++;
				//Adds episodes to the series until no more episodes are found
				while(counter < fileLines.size() && fileLines.get(counter).charAt(fileLines.get(counter).length() - 5) != '-'){
					currentWorkingSeries.add(parseEpisode(fileLines.get(counter), currentWorkingSeries.getTitle()));
			
					//Increments to the next line
					counter++;
				}
				//Adds the series to the database
				fileSeriesDatabase.addSeries(currentWorkingSeries);
			}
			else{
				counter++;
			}
		}
		return fileSeriesDatabase;
	}
	/**
	 * Parses a line into the parts necessary to construct a new movie
	 * @param movieContents Takes an arraylist of file lines
	 * @return Returns a new database object
	 */
	public static MovieDatabase parseMovie(ArrayList<String> movieContents){
		String title, releaseYear,numerals= "";
		int trueYear;
		MovieDatabase fileMovieDatabase = new MovieDatabase();
		
		//Runs for each line stored in movieContents
		for(String fileLine : movieContents){
			String fileFormattedLine = fileLine;
			String releaseType = "";
			//Splits the line into an array, seperating from the year
			String[] fileLineArray = fileLine.split("\\(\\d\\d\\d\\d|\\(\\?\\?\\?\\?");
			//Stores the first index, which is always the title, into Title
			title = fileLineArray[0].trim();
			
			
			//Checks if the line contains a / indicating the existence of a roman numeral
			if(fileLineArray[1].charAt(0) == '/'){
				numerals = fileLineArray[1].substring(1, fileLineArray[1].indexOf(')')); 
			}
			//Takes the four digits at the end of the movie object as its release year.
			releaseYear = fileLine.substring(fileLine.length() - 4, fileLine.length());
			
			//Attempts to parse the year, if it fails, the year is unknown and is set to -1.
			try{
				trueYear = Integer.parseInt(releaseYear);
			}catch(NumberFormatException e){
				trueYear = -1;
			}
			//Checks the existence of a media type
			if (fileLine.contains("(TV)")){
				releaseType = " (TV)";
			}else if(fileLine.contains("(V)")){
				releaseType = " (straight to video)";
			}
			//constructs and immediately adds the movies to the MovieDatabase
			fileMovieDatabase.addMovie(new Movie(title, releaseType, numerals, trueYear, fileFormattedLine));
		}
		//Returns the place in memory in which all movies were added to a database
		System.out.println(fileMovieDatabase.size());
		return fileMovieDatabase;
	}
	/**
	 * Parses a line into the parts necessary to construct a new series.
	 * @param fileLine Takes a line of a file
	 * @return Returns a new series
	 */
	private static Series parseSeries(String fileLine){
		String fileFormattedLine = fileLine;
		String Title = "";
		int startYear = 0;
		int endYear = 0;
		
		//If does not contain an episode, this is the start of a new series
		//Parse the start of a new series
		int StringIndex = 1;
		
		//Takes the title of the series starting at the first " and runs until the next "
		while(fileLine.charAt(StringIndex) != '"'){
			StringIndex++;
		}
		Title = fileLine.substring(1, StringIndex);
		
		//Checks if a year is not given. If it is, it sets the end year to the respective digits at the end of the line
		if(fileLine.charAt(fileLine.length() - 1) == '?'){
			endYear = -1;
		}else{
			endYear = Integer.parseInt(fileLine.substring(fileLine.length() - 4,fileLine.length()));
		}
		//Start year is always set to the four digits before the end digits
		startYear = Integer.parseInt(fileLine.substring(fileLine.length() - 9, fileLine.length() - 5));	
		
		//Returns a Series object of the parsed information
		return new Series(Title, startYear, endYear, fileFormattedLine);
	}
	/**
	 * Parses a line into the parts necessary to construct a new episode
	 * @param fileLine Takes a line of a file
	 * @param seriesName Takes the name of the series the episode belongs to
	 * @return Returns a new episode
	 */
	private static Episode parseEpisode(String fileLine, String seriesName){
		String fileFormattedLine = fileLine;
		int episodeYear = 0;
		String episodeTitle = "";
		boolean isSuspended = false;
		
		int indexOfTitleStart = fileLine.indexOf('{') + 1;
		int indexOfTitleEnd = indexOfTitleStart;
		
		//Parses the title information
		while(fileLine.charAt(indexOfTitleEnd) != '}' && indexOfTitleEnd < fileLine.length()){
			indexOfTitleEnd++;
		}
		
		episodeTitle = fileLine.substring(indexOfTitleStart, indexOfTitleEnd );
		
		if(episodeTitle.charAt(0) != '('){
			episodeTitle = episodeTitle.split(" \\(#")[0];
		}
		//Searches for if the episode is suspended
		if(fileLine.contains("{{SUSPENDED}}")){
			isSuspended = true;
		}
		
		//Attempts to parse the digits into a year, it they do not exist, sets it to -1.
		try{
			episodeYear = Integer.parseInt(fileLine.substring(fileLine.length() - 4, fileLine.length()));
		}
		catch(NumberFormatException e){
			episodeYear = -1;
		}
		//Returns a new episode object
		return new Episode(episodeTitle, episodeYear, isSuspended, seriesName, fileFormattedLine);
	}
}
