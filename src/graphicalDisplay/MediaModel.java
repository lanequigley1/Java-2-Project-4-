package graphicalDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import database.Database;
import database.MediaMakerDatabase;
import database.MovieDatabase;
import database.SeriesDatabase;
import mediaTypes.Episode;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;
import people.MediaMaker;

public class MediaModel extends Database {
	
	ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	
	public MediaModel(MediaMakerDatabase makerDb, MovieDatabase movieDb, SeriesDatabase seriesDb) {
		super(makerDb, movieDb, seriesDb);
	}
	
	public MediaMakerDatabase getMakerDB(){
		return super.makerDb;
	}
	public ArrayList<Media> getMediaList(){
		ArrayList<Media> media = new ArrayList<Media>(100);
		media.addAll(this.makerDb.getMediaMakerMap().values());
		media.addAll(this.movieDb.toArrayList());
		media.addAll(this.seriesDb.toArrayList());
		media.addAll(this.seriesDb.getAllEpisodes());
		return media;
	}
	public void addAllMovies(ArrayList<Movie> movieList) {
		super.addAllMovies(movieList);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add all movies"));
	}
	
	public void addAllSeries(ArrayList<Series> seriesList) {
		super.addAllSeries(seriesList);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add all series"));
	}
	
	public void putAllPeople(LinkedHashMap<String, MediaMaker> people) {
		super.putAll(people);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "put all people"));
	}
	
	public void addMovie(Movie movie) {
		super.addMovie(movie);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add movie"));
	}
	
	public void addSeries(Series series) {
		super.addSeries(series);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add series"));
	}
	
	public void addEpisode(Episode episode) {
		super.addEpisode(episode);
	}
	
	public void addPerson(MediaMaker person) {
		super.addPerson(person);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add person"));
	}

	public void editMovie(int index, Movie m) {
		super.editMovie(index, m);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit movie"));
	}
	
	public void editSeries(int index, Series s) {
		super.editSeries(index, s);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit series"));
	}
	
	public void editEpisode(String seriesTitle, String oldEpisodeTitle, Episode myNewEpisode) {
		super.editEpisode(seriesTitle, oldEpisodeTitle, myNewEpisode);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit episode"));
	}
	
	public void editPerson(String key, MediaMaker m) {
		super.editPerson(key, m);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit person"));
	}
	
	public void deleteEpisode(String seriesTitle, String oldEpisodeTitle) {
		super.deleteEpisode(seriesTitle, oldEpisodeTitle);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "delete episode"));
	}
	
	public void deleteMovie(int index) {
		super.deleteMovie(index);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "delete movie"));
	}
	
	public void deleteSeries(int index) {
		super.deleteSeries(index);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "delete series"));
	}
	
	public LinkedList<Movie> getMovieList(){
		return super.getMovieList();
	}
	
	public void deletePerson(String key) {
		super.deletePerson(key);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "delete person"));
	}
	
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}
	
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}
	
	@SuppressWarnings("unchecked")
	public void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;
		
		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>) actionListenerList.clone();
		}
		
		for (ActionListener listener : list) {
			listener.actionPerformed(e);
		}
	}

}
