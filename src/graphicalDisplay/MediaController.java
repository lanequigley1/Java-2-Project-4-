package graphicalDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.MainDriver;

import database.MediaMakerDatabase;
import database.MovieDatabase;
import database.SeriesDatabase;
import mediaTypes.Episode;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;
import parsers.Parser;
import parsers.PeopleParser;
import people.MediaMaker;

public class MediaController extends SelectionView{

	private static final long serialVersionUID = -283197653041868275L;
	private static boolean episodesEnabled = false; // Becomes true once a series has been added
	private static File[] fileArray = new File[500];
	
	private static MediaModel model;
	private static SelectionView selectionView;
	
	public MediaController(MediaModel model){
		MediaController.model = model;
	}
	/**
	 * Main controller functionality, listens for any actions.
	 */
	protected static void addSelectionViewActionListeners(){

		save.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Save Button Hit");
			 }
		});
		Import.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				edit.setEnabled(true);
				delete.setEnabled(true);
				clear.setEnabled(true);
				clearAll.setEnabled(true);
				histogram.setEnabled(true);
				pieChart.setEnabled(true);
				fileArray = FileViews.importFileChooser();
				System.out.println("There are " + fileArray.length + " files in the array");
				SelectionView.edit.setEnabled(true);
				SelectionView.delete.setEnabled(true);
			 }
		});
		Export.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Export Button Hit");
			  fileArray = FileViews.exportFileChooser();
			 }
		});
		add.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				if(MoviesRadioButton.isSelected()){
					  AddEditViews.movieAddWindow();
				  }else if(SeriesRadioButton.isSelected()){
					  AddEditViews.seriesAddWindow();
				  }else if(EpisodesRadioButton.isSelected() && episodesEnabled == true){
					  AddEditViews.episodeAddWindow();
				  }else if(DirectorsRadioButton.isSelected() || ProducerRadioButton.isSelected() || 
						  ActorsRadioButton.isSelected() || MakersRadioButton.isSelected()){
					  AddEditViews.makerAddWindow();
				  }
				  SelectionView.jScrollPane.revalidate();
				  SelectionView.jScrollPane.getViewport().revalidate();
			}
		});
		edit.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				if(MoviesRadioButton.isSelected()){
					AddEditViews.editMovie();
				  }else if(SeriesRadioButton.isSelected()){
					  AddEditViews.seriesEditWindow();
				  }else if(EpisodesRadioButton.isSelected() && episodesEnabled == true){
					  AddEditViews.episodeEditWindow();
				  }else if(DirectorsRadioButton.isSelected() || ProducerRadioButton.isSelected() || 
						  ActorsRadioButton.isSelected() || MakersRadioButton.isSelected()){
					  AddEditViews.makerEditWindow();
				  }
			 }
		});
		delete.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Button Hit");
				if(FileViews.deleteConfirmation() == 1){
					//delete the things
				}
			 }
		});
		clear.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				JList list = (JList) jScrollPane.getViewport().getView();
				list.remove(list.getSelectedIndex());
				System.out.println("Clear Button Hit");
			 }
		});
		clearAll.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				model.clearAll();
				System.out.println("ClearAll Button Hit");
			 }
		});
		pieChart.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Pie Chart Button Hit");
			 }
		});
		histogram.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Histogram Button Hit");
			 }
		});
		MediaRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Media Button Hit");
			  add.setEnabled(false);
			  add.setToolTipText("");
			  @SuppressWarnings({ "rawtypes", "unchecked" })
			  ArrayList<Media> media = new ArrayList<Media>(100);
				media.addAll(model.makerDb.getMediaMakerMap().values());
				media.addAll(model.movieDb.toArrayList());
				media.addAll(model.seriesDb.toArrayList());
				media.addAll(model.seriesDb.getAllEpisodes());
			  JList list = new JList(media.toArray());
			  updateWindow(list);
			 }
		});
		MoviesRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				System.out.println("Movies Button Hit");
				add.setEnabled(true);
				add.setToolTipText("");
				if(model.getMovieList() != null){
					@SuppressWarnings({ "rawtypes", "unchecked" })
					JList list = new JList(model.getMovieList().toArray());
					updateWindow(list);
				}
			 }
		});
		SeriesRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Series Button Hit");
			  add.setEnabled(true);
			  add.setToolTipText("");
			  @SuppressWarnings({ "unchecked", "rawtypes" })
			  JList list = new JList(model.getSeriesList().toArray());
			  updateWindow(list);
			 }
		});
		EpisodesRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Episodes Button Hit");
			  add.setEnabled(false);
			  if(episodesEnabled  == true){
				  add.setEnabled(true);
				  add.setToolTipText("");
				  @SuppressWarnings({ "rawtypes", "unchecked" })
				  JList list = new JList(model.getAllEpisodes().toArray());
				  updateWindow(list);
			  }  			
			}
		});
		MakersRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Makers Button Hit");
			  add.setEnabled(true);
			  add.setToolTipText("");
			  @SuppressWarnings({ "rawtypes", "unchecked" })
			  JList list = new JList(model.getAllPeople().toArray());
			  updateWindow(list);
			 }
		});
		ActorsRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Actors Button Hit");
			  add.setEnabled(true);
			  add.setToolTipText("");
			  @SuppressWarnings({ "rawtypes", "unchecked" })
			  JList list = new JList(model.getAllActors().toArray());
			  updateWindow(list);
			 }
		});
		DirectorsRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Directors Button Hit");
			  add.setEnabled(true);
			  add.setToolTipText("");
			  @SuppressWarnings({ "rawtypes", "unchecked" })
			  JList list = new JList(model.getAllDirectors().toArray());
			  updateWindow(list);
			 }
		});
		ProducerRadioButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Producer Button Hit");
			  add.setEnabled(true);
			  add.setToolTipText("");
			  @SuppressWarnings({ "rawtypes", "unchecked" })
			  JList list = new JList(model.getAllProducers().toArray());
			  updateWindow(list);
			 }
		});
	}
	
	protected static void addFileViewsActionListeners(){
		FileViews.importFileChooser.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				FileViews.fileTypeImporting();
			}
		});
		FileViews.saveFileChooser.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				FileViews.fileTypeExporting();
			}
		});
		FileViews.fileTypeConfirmButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				System.out.println("FileTypeConfirm hit");
				if(FileViews.MovieFileTypeRadioButton.isSelected()){
					System.out.println("Importing Movie File");
					model.addAllMovies(Parser.parseMovie(MainDriver.importTextFile(fileArray)).toArrayList());
					FileViews.importFrame.dispose();
				}else if(FileViews.SeriesFileTypeButton.isSelected()){
					System.out.println("Importing series file");
					model.addAllSeries(Parser.parseSeriesDatabase((MainDriver.importTextFile(fileArray))).toArrayList());
					FileViews.importFrame.dispose();
				}else if(FileViews.ActorFileTypeRadioButton.isSelected()){
					System.out.println("Importing Actor file");
					PeopleParser.parseMediaMakers(MainDriver.importTextFile(fileArray), PeopleParser.ACTING_FILE, model.makerDb, model.movieDb, model.seriesDb);
					FileViews.importFrame.dispose();
				}else if(FileViews.DirectorFileTypeRadioButton.isSelected()){
					System.out.println("Importing Director file");
					PeopleParser.parseMediaMakers(MainDriver.importTextFile(fileArray), PeopleParser.DIRECTING_FILE, model.makerDb, model.movieDb, model.seriesDb);
					FileViews.importFrame.dispose();
				}else if(FileViews.ProducerFileTypeRadioButton.isSelected()){
					System.out.println("Importing Producer file");
					PeopleParser.parseMediaMakers(MainDriver.importTextFile(fileArray), PeopleParser.PRODUCING_FILE, model.makerDb, model.movieDb, model.seriesDb);
					FileViews.importFrame.dispose();
				}
				if(!model.getMovieList().isEmpty() || !model.makerDb.getMediaMakerMap().isEmpty() || 
						!model.getSeriesList().isEmpty()){
					SelectionView.Export.setEnabled(true);
					SelectionView.save.setEnabled(true);
				}
			 }
		});
		FileViews.exportButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
			  System.out.println("Export Button Hit");
			  if(FileViews.MovieFileTypeRadioButton.isSelected()){
					System.out.println("Exporting Movie File");
					MainDriver.saveTextFile(fileArray, MovieDatabase.formatFriendlyText(model.movieDb.toArrayList()));
					FileViews.exportFrame.dispose();
				}else if(FileViews.SeriesFileTypeButton.isSelected()){
					System.out.println("Exporting series file");
					MainDriver.saveTextFile(fileArray, SeriesDatabase.formatFriendlyText(model.seriesDb.toArrayList()));
					FileViews.exportFrame.dispose();
				}else if(FileViews.ActorFileTypeRadioButton.isSelected()){
					System.out.println("Exporting Actor file");
					MainDriver.saveTextFile(fileArray, MediaMakerDatabase.formatTextFriendlyModel(PeopleParser.ACTING_FILE, 
							model.getMakerDB().getMediaMakerMap()));
					FileViews.exportFrame.dispose();
				}else if(FileViews.DirectorFileTypeRadioButton.isSelected()){
					System.out.println("Exporting Director file");
					MainDriver.saveTextFile(fileArray, MediaMakerDatabase.formatTextFriendlyModel(PeopleParser.DIRECTING_FILE, 
							model.getMakerDB().getMediaMakerMap()));
					FileViews.exportFrame.dispose();
				}else if(FileViews.ProducerFileTypeRadioButton.isSelected()){
					System.out.println("Exporting Producer file");
					MainDriver.saveTextFile(fileArray, MediaMakerDatabase.formatTextFriendlyModel(PeopleParser.PRODUCING_FILE, 
							model.getMakerDB().getMediaMakerMap()));
					FileViews.exportFrame.dispose();
				}
			  
			  FileViews.exportFrame.dispose();
			 }
		});
	}
	
	protected static void addEditAddViewsActionListeners(){
		AddEditViews.movieAddButton.addActionListener(new ActionListener(){
			@Override
			 public void actionPerformed(ActionEvent e) {
				System.out.println("Movie OK Button Hit");
				System.out.println(AddEditViews.yearField.getText());
				
				
				model.addMovie(new Movie(AddEditViews.nameField.getText(), AddEditViews.releaseMediaLabel.getText(),AddEditViews.numeralRelease.getText(),
				Integer.parseInt(AddEditViews.yearField.getText()),	Boolean.parseBoolean(AddEditViews.suspensionStatus.getText())));
			 }
		});
		AddEditViews.seriesAddButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Series Ok Button Hit");
				
				
				model.addSeries(new Series(AddEditViews.nameField.getText(), Integer.parseInt(AddEditViews.startYearField.getText()), 
				Integer.parseInt(AddEditViews.endYearField.getText()), ""));
			}
		});
		AddEditViews.episodeAddButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Episode Ok Button Hit.");
				model.addEpisode(new Episode(AddEditViews.episodeTitleField.getText(), Integer.parseInt(AddEditViews.episodeYearField.getText()),
						Boolean.parseBoolean(AddEditViews.episodeSuspensionStatus.getText()), AddEditViews.episodeSeriesTitle.getText(), ""));
			}
		});
		AddEditViews.makerAddButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Person OK Button Hit.");
				
	
				model.addPerson(new MediaMaker());
				

			}
		});
		AddEditViews.movieEditButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Movie Edit OK Button Hit.");
				
				/*model.editMovie(list.getSelectedIndex(), new Movie(AddEditViews.nameField.getText(), AddEditViews.releaseMediaLabel.getText(),AddEditViews.numeralRelease.getText(),
						Integer.parseInt(AddEditViews.yearField.getText()),	Boolean.parseBoolean(AddEditViews.suspensionStatus.getText())));
				*/		
				
			}
		});
		AddEditViews.seriesEditButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Series Edit OK Button Hit.");
			/*	
				model.editSeries(list.getSelectedIndex(), new Series(AddEditViews.nameField.getText(), Integer.parseInt(AddEditViews.startYearField.getText()), 
				Integer.parseInt(AddEditViews.endYearField.getText()), ""));
			*/	
			}
		});
		AddEditViews.episodeEditButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Episode Edit OK Button Hit.");
				
				//Episode episode = (Episode) list.getSelectedValue();
				
			/*	model.editEpisode(episode.getSeriesName(), episode.getTitle(), 
						new Episode(AddEditViews.episodeTitleField.getText(), Integer.parseInt(AddEditViews.episodeYearField.getText()), 
								Boolean.parseBoolean(AddEditViews.episodeSuspensionStatus.getText()), AddEditViews.episodeSeriesTitle.getText(), ""));
				*/
			}
		});
		AddEditViews.makerEditButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.out.println("Person Edit OK Button Hit.");
				
				//MediaMaker maker = (MediaMaker) list.getSelectedValue();
				
				//TODO: add arguments
				//model.editPerson(maker.getFullName(), new MediaMaker());
			}
		});
		
		
	}
	/**
	 * Updates window
	 * @param list List to update
	 */
	private static void updateWindow(@SuppressWarnings("rawtypes") JList list){
		jScrollPane.getViewport().add(list);
		SelectionView.jScrollPane.revalidate();
		SelectionView.jScrollPane.repaint();
	}
	
}
