package graphicalDisplay;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mediaTypes.Episode;
import mediaTypes.Movie;
import mediaTypes.Series;
import people.MediaMaker;

public class AddEditViews extends SelectionView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6364877161221579032L;
	public static JFrame windowFrame = new JFrame();
	public static JButton movieAddButton = new JButton();
	public static JButton movieEditButton = new JButton();
	public static JPanel textPanel = new JPanel();
	
	public static JTextField nameField = new JTextField();
	public static JLabel nameLabel = new JLabel("Name", JLabel.LEFT);
	public static JPanel jplName = new JPanel(new GridLayout(1, 0, 5, 5));
	
	public static JTextField yearField = new JTextField();
	public static JLabel yearLabel = new JLabel("Year", JLabel.LEFT);
	public static JPanel jplYear = new JPanel(new GridLayout(1, 0, 5, 5));
	
	public static JTextField suspensionStatus = new JTextField();
	public static JLabel suspendedLabel = new JLabel("Suspension Status", JLabel.LEFT);
	public static JPanel jplSuspension = new JPanel(new GridLayout(1, 0, 5, 5));
	
	public static JTextField releaseMedia = new JTextField();
	public static JLabel releaseMediaLabel = new JLabel("Release Media", JLabel.LEFT);
	public static JPanel jplReleaseMedia = new JPanel(new GridLayout(1, 0, 5, 5));
	
	public static JTextField numeralRelease = new JTextField();
	public static JLabel numeralReleaseLabel = new JLabel("Numeral Release", JLabel.LEFT);
	public static JPanel jplNumeralRelease = new JPanel(new GridLayout(1, 0, 5, 5));
	
	public static void movieAddWindow(){
		nameField.setText("");
		yearField.setText("");
		suspensionStatus.setText("");
		releaseMedia.setText("");
		numeralRelease.setText("");
		windowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowFrame.setTitle("Add Movie");
		
		jplName.add(nameLabel);
		jplName.add(nameField);
		
		jplYear.add(yearLabel);
		jplYear.add(yearField);
		
		jplSuspension.add(suspendedLabel);
		jplSuspension.add(suspensionStatus);
		
		jplReleaseMedia.add(releaseMediaLabel);
		jplReleaseMedia.add(releaseMedia);
		
		jplNumeralRelease.add(numeralReleaseLabel);
		jplNumeralRelease.add(numeralRelease);
		
		windowFrame.add(jplName);
		windowFrame.add(jplYear);
		windowFrame.add(jplSuspension);
		windowFrame.add(jplReleaseMedia);
		windowFrame.add(jplNumeralRelease);
		movieAddButton.setSize(new Dimension(50, 50));
		movieAddButton.setText("Add");
		windowFrame.add(movieAddButton);
		windowFrame.setSize(300, 300);
		//windowFrame.pack();
		windowFrame.setVisible(true);
	}
	/**
	 * Edit window 
	 */
	public static void editMovie(){
		JList list = (JList) jScrollPane.getViewport().getView();
		if(list.getSelectedValue() == null){
			System.out.println("This is null");
			return;
		}
		Movie M = new Movie();
		if(list.getSelectedValue() instanceof Movie){
			M = (Movie) list.getSelectedValue();
		}else{
			System.out.println("This object is not a movie. This should not happen.");
			return;
		}
		windowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		windowFrame.setTitle("Edit Movie");
				
		nameField.setText(M.getTitle());
		jplName.add(nameLabel);
		jplName.add(nameField);
		yearField.setText(Integer.toString(M.getStartYear()));
		jplYear.add(yearLabel);
		jplYear.add(yearField);
		
		suspensionStatus.setText(Boolean.toString(M.getIsSuspended()));
		jplSuspension.add(suspendedLabel);
		jplSuspension.add(suspensionStatus);
		
		releaseMedia.setText(M.getReleaseMedia());
		jplReleaseMedia.add(releaseMediaLabel);
		jplReleaseMedia.add(releaseMedia);
		
		numeralRelease.setText(M.getNumeralRelease());
		jplNumeralRelease.add(numeralReleaseLabel);
		jplNumeralRelease.add(numeralRelease);
		
		windowFrame.add(jplName);
		windowFrame.add(jplYear);
		windowFrame.add(jplSuspension);
		windowFrame.add(jplReleaseMedia);
		windowFrame.add(jplNumeralRelease);
		nameField.setToolTipText("Name");
		nameField.setText("Name");
		movieEditButton.setSize(new Dimension(50, 50));
		movieEditButton.setText("Edit");
		windowFrame.add(movieAddButton);
		windowFrame.setSize(300, 300);
		//windowFrame.pack();
		windowFrame.setVisible(true);
	}
	
	public static JFrame episodeWindowFrame = new JFrame();
	public static JButton episodeAddButton = new JButton();
	public static JButton episodeEditButton = new JButton();
	public static JPanel episodeTextPanel = new JPanel();
	public static JTextField episodeTitleField = new JTextField();
	public static JLabel episodeTitleLabel = new JLabel("Title", JLabel.LEFT);
	public static JPanel jplEpisodeTitle = new JPanel(new GridLayout(1, 0, 5, 5));
	public static JTextField episodeYearField = new JTextField();
	public static JLabel episodeYearLabel = new JLabel("Year", JLabel.LEFT);
	public static JPanel jplEpisodeYear = new JPanel(new GridLayout(1, 0, 5, 5));
	public static JTextField episodeSuspensionStatus = new JTextField();
	public static JLabel episodeSuspendedLabel = new JLabel("Suepension", JLabel.LEFT);
	public static JPanel jplEpisodeSuspension = new JPanel(new GridLayout(1, 0, 5, 5));
	public static JTextField episodeSeriesTitle = new JTextField();
	public static JLabel episodeSeriesLabel = new JLabel("Series Title", JLabel.LEFT);
	public static JPanel jplEpisodeSeriesTitle = new JPanel(new GridLayout(1, 0, 5, 5));
	/**
	 * Episode add window
	 */
	public static void episodeAddWindow()
	{
		episodeTitleField.setText("");
		episodeYearField.setText("");
		episodeSuspensionStatus.setText("");
		episodeSeriesTitle.setText("");
		episodeWindowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		episodeWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		episodeWindowFrame.setTitle("Add Episode");
		
		jplEpisodeTitle.add(episodeTitleLabel);
		jplEpisodeTitle.add(episodeTitleField);
		jplEpisodeYear.add(episodeYearLabel);
		jplEpisodeYear.add(episodeYearField);
		jplEpisodeSuspension.add(episodeSuspendedLabel);
		jplEpisodeSuspension.add(episodeSuspensionStatus);
		jplEpisodeSeriesTitle.add(episodeSeriesLabel);
		jplEpisodeSeriesTitle.add(episodeSeriesTitle);
		episodeWindowFrame.add(jplEpisodeTitle);
		episodeWindowFrame.add(jplEpisodeYear);
		episodeWindowFrame.add(jplEpisodeSuspension);
		episodeWindowFrame.add(jplEpisodeSeriesTitle);
		episodeTitleField.setToolTipText("Title");
		episodeTitleField.setText("Title");
		episodeAddButton.setSize(new Dimension(50, 50));
		episodeAddButton.setText("Add");
		episodeWindowFrame.add(episodeAddButton);
		episodeWindowFrame.setSize(300, 300);
		//windowFrame.pack();
		episodeWindowFrame.setVisible(true);
	}
	/**
	 * Episode edit window
	 */
	protected static void episodeEditWindow(){
		Episode e = new Episode();
		JList list = (JList) jScrollPane.getViewport().getView();
		if(list.getSelectedValue() instanceof Episode){
			e = (Episode) list.getSelectedValue();
		}else{
			System.out.println("This is not an instance of episode.");
			return;
		}
		episodeTitleField.setText(e.getTitle());
		episodeYearField.setText(Integer.toString(e.getStartYear()));
		episodeSuspensionStatus.setText(Boolean.toString(e.getSuspendedStatus()));
		episodeSeriesTitle.setText(e.getSeriesName());
		episodeWindowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		episodeWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		episodeWindowFrame.setTitle("Edit Episode");
		
		jplEpisodeTitle.add(episodeTitleLabel);
		jplEpisodeTitle.add(episodeTitleField);
		jplEpisodeYear.add(episodeYearLabel);
		jplEpisodeYear.add(episodeYearField);
		jplEpisodeSuspension.add(episodeSuspendedLabel);
		jplEpisodeSuspension.add(episodeSuspensionStatus);
		jplEpisodeSeriesTitle.add(episodeSeriesLabel);
		jplEpisodeSeriesTitle.add(episodeSeriesTitle);
		episodeWindowFrame.add(jplEpisodeTitle);
		episodeWindowFrame.add(jplEpisodeYear);
		episodeWindowFrame.add(jplEpisodeSuspension);
		episodeWindowFrame.add(jplEpisodeSeriesTitle);
		episodeTitleField.setToolTipText("Title");
		episodeTitleField.setText("Title");
		episodeEditButton.setSize(new Dimension(50, 50));
		episodeEditButton.setText("Edit");
		episodeWindowFrame.add(episodeAddButton);
		episodeWindowFrame.setSize(300, 300);
		//windowFrame.pack();
		episodeWindowFrame.setVisible(true);
	}
	
	public static JFrame seriesWindowFrame = new JFrame();
	public static JButton seriesAddButton = new JButton();
	public static JButton seriesEditButton = new JButton();
	public static JPanel seriesTextPanel = new JPanel();
	public static JTextField startYearField = new JTextField();
	public static JLabel startYearLabel = new JLabel("Start Year", JLabel.LEFT);
	public static JPanel jplStartYear = new JPanel(new GridLayout(1, 0, 5, 5));
	public static JTextField endYearField = new JTextField();
	public static JLabel endYearLabel = new JLabel("End Year", JLabel.LEFT);
	public static JPanel jplEndYear = new JPanel(new GridLayout(1, 0, 5, 5));
	/**
	 * Add window for series
	 */
	public static void seriesAddWindow(){
		startYearField.setText("");
		endYearField.setText("");
		nameField.setText("");
		seriesWindowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		seriesWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		seriesWindowFrame.setTitle("Add Series");
		
		jplName.add(nameLabel);
		jplName.add(nameField);
		jplStartYear.add(startYearLabel);
		jplStartYear.add(startYearField);
		jplEndYear.add(endYearLabel);
		jplEndYear.add(endYearField);
		seriesWindowFrame.add(jplName);
		seriesWindowFrame.add(jplStartYear);
		seriesWindowFrame.add(jplEndYear);
		nameField.setToolTipText("Name");
		nameField.setText("Name");
		seriesAddButton.setSize(new Dimension(50, 50));
		seriesAddButton.setText("Add");
		seriesWindowFrame.add(seriesAddButton);
		seriesWindowFrame.setSize(300, 300);
		//windowFrame.pack();
		seriesWindowFrame.setVisible(true);
	}
	public static void seriesEditWindow(){
		Series s = new Series();
		JList list = (JList) jScrollPane.getViewport().getView();
		if(list.getSelectedValue() instanceof Series){
			s = (Series) list.getSelectedValue();
		}else{
			System.out.println("Selected value was not a person.");
			return;
		}
		startYearField.setText(Integer.toString(s.getStartYear()));
		endYearField.setText(Integer.toString(s.getEndYear()));
		nameField.setText(s.getTitle());
		seriesWindowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		seriesWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		seriesWindowFrame.setTitle("Edit Series");
		
		jplName.add(nameLabel);
		jplName.add(nameField);
		jplStartYear.add(startYearLabel);
		jplStartYear.add(startYearField);
		jplEndYear.add(endYearLabel);
		jplEndYear.add(endYearField);
		seriesWindowFrame.add(jplName);
		seriesWindowFrame.add(jplStartYear);
		seriesWindowFrame.add(jplEndYear);
		nameField.setToolTipText("Name");
		nameField.setText("Name");
		seriesEditButton.setSize(new Dimension(50, 50));
		seriesEditButton.setText("Edit");
		seriesWindowFrame.add(seriesAddButton);
		seriesWindowFrame.setSize(300, 300);
		//windowFrame.pack();
		seriesWindowFrame.setVisible(true);
	}
	
	public static JFrame makerWindowFrame = new JFrame();
	public static JButton makerAddButton = new JButton();
	public static JButton makerEditButton = new JButton();
	public static JPanel makerTextPanel = new JPanel();
	public static JTextField FirstNameField = new JTextField();
	public static JLabel FirstNameLabel = new JLabel("First Name", JLabel.LEFT);
	public static JPanel jplFirstName = new JPanel(new GridLayout(1,0, 5, 5));
	public static JTextField LastNameField = new JTextField();
	public static JLabel LastNameLabel = new JLabel("Last Name", JLabel.LEFT);
	public static JPanel jplLastName = new JPanel(new GridLayout(1, 0, 5, 5));
	public static JTextField nameNumeralField = new JTextField();
	public static JLabel nameNumeralLabel = new JLabel("Disambiguation Numeral", JLabel.LEFT);
	public static JPanel jplNameNumeral = new JPanel(new GridLayout(1, 0, 5, 5));
	
	public static void makerAddWindow(){
		FirstNameField.setText("");
		LastNameField.setText("");
		nameNumeralField.setText("");
		makerWindowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		makerWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		makerWindowFrame.setTitle("Add Person");
		
		jplFirstName.add(FirstNameLabel);
		jplFirstName.add(FirstNameField);
		jplLastName.add(LastNameLabel);
		jplLastName.add(LastNameField);
		jplNameNumeral.add(nameNumeralLabel);
		jplNameNumeral.add(nameNumeralField);
		makerWindowFrame.add(jplFirstName);
		makerWindowFrame.add(jplLastName);
		makerWindowFrame.add(jplNameNumeral);
		makerAddButton.setSize(new Dimension(50, 50));
		makerAddButton.setText("Add");
		makerWindowFrame.add(makerAddButton);
		makerWindowFrame.setSize(300, 300);
		//windowFrame.pack();
		makerWindowFrame.setVisible(true);
	}
	public static void makerEditWindow(){
		MediaMaker m = new MediaMaker();
		JList list = (JList) jScrollPane.getViewport().getView();
		if(list.getSelectedValue() instanceof MediaMaker){
			m = (MediaMaker) list.getSelectedValue();
		}else{
			System.out.println("Selected value was not a person.");
			return;
		}
		FirstNameField.setText(m.getFirstName());
		LastNameLabel.setText(m.getLastName());
		nameNumeralField.setText(m.getNumeralNumber());
		makerWindowFrame.setLayout(new GridLayout(0, 1, 5, 5));
		makerWindowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		makerWindowFrame.setTitle("Edit Person");
		
		jplFirstName.add(FirstNameLabel);
		jplFirstName.add(FirstNameField);
		jplLastName.add(LastNameLabel);
		jplLastName.add(LastNameField);
		jplNameNumeral.add(nameNumeralLabel);
		jplNameNumeral.add(nameNumeralField);
		makerWindowFrame.add(jplFirstName);
		makerWindowFrame.add(jplLastName);
		makerWindowFrame.add(jplNameNumeral);
		makerEditButton.setSize(new Dimension(50, 50));
		makerEditButton.setText("Edit");
		makerWindowFrame.add(makerAddButton);
		makerWindowFrame.setSize(300, 300);
		//windowFrame.pack();
		makerWindowFrame.setVisible(true);
	}
}
