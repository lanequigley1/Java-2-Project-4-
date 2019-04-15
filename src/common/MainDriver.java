package common;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import database.MediaMakerDatabase;
import database.MovieDatabase;
import database.SeriesDatabase;
import graphicalDisplay.MediaController;
import graphicalDisplay.MediaModel;
import graphicalDisplay.SelectionView;

/**
 * Project #2
 * CS 2334, Section 010
 * February 18, 2016
 * <P>
 * Drives the program through file reading, user input and method calls.
 * </P>
 * @version 1.0
 * 
 */
public class MainDriver {
	/**
	 * Main class
	 * @param args Command line args
	 * @throws IOException Throws exception if the user messes up.
	 */
	public static void main(String[]args)throws IOException{
		
		MovieDatabase movieDB = new MovieDatabase();
		SeriesDatabase seriesDB = new SeriesDatabase();
		MediaMakerDatabase mediaMakersDB = new MediaMakerDatabase();
		MediaModel model = new MediaModel(mediaMakersDB, movieDB, seriesDB);
		SelectionView selectionView = new SelectionView();
		MediaController controller = new MediaController(model);
		selectionView.setModel(model);
		System.out.println("model will be set");
	}
	/**
	 * Allows the user to save text file
	 * @param fileArray Array of files to save
	 * @param data Data to be saved
	 */
	public static void saveTextFile(File[] fileArray, String data){
		for(File f : fileArray){
			PrintWriter out;
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
				out.write(data);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Saving text file failed.");
				return;
			}
		}
	}
	/**
	 * Allows the user to export the file
	 * @param fileName Name of files
	 * @param data Data to be saved
	 */
	public static void saveBinaryFile(String fileName, String data){
		try{
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
			dos.writeChars(data);
			dos.close();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Saving to binary failed.");
			return;
		}
	}
	/**
	 * Allows the user to import an array of files
	 * @param fileArray Array of files to be imported
	 * @return Returns one arraylist of strings read from the files.
	 */
	public static ArrayList<String> importTextFile(File[] fileArray){
		ArrayList<String> FileContents = new ArrayList<String>(100);
		String fileLine = "";
		for(File f : fileArray){
			try{
				BufferedReader fileReader = new BufferedReader(new FileReader(f));
				while(fileReader.ready()){
					fileLine = fileReader.readLine();
					FileContents.add(fileLine);
				}
				fileReader.close();
			} catch (IOException e) {
			 	e.printStackTrace();
			 	System.out.println("Reading text in failed");
				return FileContents;
		 	}
		}
		return FileContents;
	}
}
