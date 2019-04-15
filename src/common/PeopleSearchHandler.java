package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import database.MediaMakerDatabase;
import graphicalDisplay.PieChart;
import graphicalDisplay.StackedBarchart;
import people.MediaMaker;
/**
 * Project #4
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Handles searching for people and their objects
 * </P>
 * @version 1.0
 * 
 */
public class PeopleSearchHandler {

	/**
	 * Handles all searching requests
	 * @param mediaMakerDatabase Database of media
	 * @param inputReader Reader for user input
	 * @return Returns the string of results 
	 * @throws IOException Throws exception if user input is invalid
	 */
	public static String generalSearching(MediaMakerDatabase mediaMakerDatabase, BufferedReader inputReader) throws IOException {
		System.out.println("Would you like to search (e)xact or (p)artial titles?"); // Gets search type
		String matchType = inputReader.readLine();
		System.out.println("What name would you like to search?"); //Gets name
		String name = inputReader.readLine();
		ArrayList<String> keys = new ArrayList<String>(5);
		if(matchType.equals("e")){
			keys = searchExactMatch(name, mediaMakerDatabase);
		}else{
			keys = getPartialMatchKeys(name, mediaMakerDatabase);
		}
		if(!keys.isEmpty()){
			System.out.println("Display (t)ext or (g)raph? Will only open one graph of the first key found.");
			String displayType = inputReader.readLine();
			if(displayType.equals("t")){
				System.out.println("Searched People");
				System.out.println("================================================================================");
				System.out.println("Searched term: " + name);
				System.out.println(turnKeysToString(keys, mediaMakerDatabase));
			}else{
				System.out.println("Display (p)ie chart or (h)istogram?");
				String graphType = inputReader.readLine();
				if(graphType.equals("p")){
					pieResults(mediaMakerDatabase, keys);
				}else{
					histogramResults(mediaMakerDatabase, keys);
				}
			}
		}
		return turnKeysToString(keys, mediaMakerDatabase);
	}
	/**
	 * Displays the histogram data
	 * @param mediaMakerDatabase Database used to get media maker contents
	 * @param keys Matches of the database
	 */
	private static void histogramResults(MediaMakerDatabase mediaMakerDatabase, ArrayList<String> keys) {
		String fullName = keys.get(0);
		MediaMaker person = mediaMakerDatabase.getMediaMakerMap().get(fullName);
		StackedBarchart barChart = new StackedBarchart(fullName, person);
		barChart.pack();
		barChart.setVisible(true);
	}
	/**
	 * Displays the pie chart data
	 * @param mediaMakerDatabase Database used to get media maker contents
	 * @param keys Matches of the database
	 */
	private static void pieResults(MediaMakerDatabase mediaMakerDatabase, ArrayList<String> keys) {
		String fullName = keys.get(0);
		MediaMaker person = mediaMakerDatabase.getMediaMakerMap().get(fullName);
		PieChart CC = new PieChart(fullName, fullName, person.getMovieActingCredit(), person.getMovieDirectedCredit(), 
			person.getMovieProducingCredit(), person.getSeriesActingCredit(),person.getSeriesDirectedCredit(), person.getSeriesProducingCredit());
		CC.pack();
		CC.setVisible(true);
	}
	/**
	 * Searches for exact matches
	 * @param name Name of person
	 * @param mediaMakerDatabase Databae of people
	 * @return Returns an arraylist of every match
	 */
	private static ArrayList<String> searchExactMatch(String name, MediaMakerDatabase mediaMakerDatabase) {
		ArrayList<String> keys = new ArrayList<String>(1);
		if(mediaMakerDatabase.getMediaMakerMap().get(name) != null){
			keys.add(name);
		}
		return keys;
	}
	/**
	 * Searches for partial matches
	 * @param name Name of person
	 * @param mediaMakerDatabase Databae of people
	 * @return Returns an arraylist of every match
	 */
	private static ArrayList<String> getPartialMatchKeys(String name, MediaMakerDatabase mediaMakerDatabase){
		Set<String> set = mediaMakerDatabase.getMediaMakerMap().keySet();
		ArrayList<String> keyMatches = new ArrayList<String>(12);
		for(String s : set){
			if(s.contains(name)){
				keyMatches.add(s);
			}
		}
		return keyMatches;
	}
	/**
	 * Turns the keys into the display data
	 * @param keyMatches All found persons
	 * @param mediaMakerDatabase Database
	 * @return Returns the results
	 */
	private static String turnKeysToString(ArrayList<String> keyMatches, MediaMakerDatabase mediaMakerDatabase){
		String data=  "";
		if(keyMatches.isEmpty()){
			return "No results found";
		}
		for(String s : keyMatches){
			data += mediaMakerDatabase.getMediaMakerMap().get(s).toString() + "\n";
		}
		if(data.isEmpty()){
			data = "No results found";
		}
		return data;
	}
}