package graphicalDisplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import mediaTypes.Media;
import mediaTypes.Movie;

public class SelectionView extends JFrame implements ActionListener{
	protected static final long serialVersionUID = -2703437491678001694L;
	
	private MediaModel model;
	
	protected static JFrame selectionViewFrame= new JFrame();
	protected static JMenuBar menuBar = new JMenuBar();
	protected static JMenu fileMenu= new JMenu("File");
	protected static JMenuItem load = new JMenuItem("Load");
	protected static JMenuItem save = new JMenuItem("Save");
	protected static JMenuItem Import = new JMenuItem("Import");
	protected static JMenuItem Export= new JMenuItem("Export");
	protected static JMenu editMenu= new JMenu("Edit");
	protected static JMenuItem add = new JMenuItem("Add");
	protected static JMenuItem edit = new JMenuItem("Edit");
	protected static JMenuItem delete = new JMenuItem("Delete");
	protected static JMenuItem clear = new JMenuItem("Clear");
	protected static JMenuItem clearAll = new JMenuItem("Clear All");
	protected static JMenu displayMenu = new JMenu("Display");
	protected static JMenuItem pieChart = new JMenuItem("Pie Chart");
	protected static JMenuItem histogram = new JMenuItem("Histogram");
	protected static JPanel scrollPanel = new JPanel();
	protected static JPanel radioPanel = new JPanel();
	//Modified by the controller
	private static TitledBorder border = new TitledBorder("Selection"); //Change
	private static TitledBorder scrollBorder = new TitledBorder("Media"); // Name is a method call
	protected static ButtonGroup radioGroup = new ButtonGroup();
	protected static JRadioButton MediaRadioButton= new JRadioButton("Media");
	protected static JRadioButton MoviesRadioButton= new JRadioButton("Movies");
	protected static JRadioButton SeriesRadioButton= new JRadioButton("Series");
	protected static JRadioButton EpisodesRadioButton= new JRadioButton("Episodes");
	protected static JRadioButton MakersRadioButton= new JRadioButton("Makers");
	protected static JRadioButton ActorsRadioButton= new JRadioButton("Actors");
	protected static JRadioButton DirectorsRadioButton= new JRadioButton("Directors");
	protected static JRadioButton ProducerRadioButton= new JRadioButton("Producer");
	
	protected static JList defaultList = new JList();
	protected static JScrollPane jScrollPane = new JScrollPane();
	/**
	 * Default Constructor
	 */
	public SelectionView() {

	}
	/**
	 * Constructor for creating the scroll panel
	 */
	private void createScrollPanel() {
		scrollBorder.setTitleJustification(TitledBorder.CENTER);
		scrollBorder.setTitlePosition(TitledBorder.TOP);
		scrollPanel.setLayout(new BorderLayout());
		scrollPanel.setPreferredSize(new Dimension(250, 100));
		scrollPanel.setBorder(scrollBorder);
		scrollPanel.add(jScrollPane, BorderLayout.CENTER);
		selectionViewFrame.add(scrollPanel, BorderLayout.EAST);
	}
	/**
	 * Creates menu bar
	 */
	private void createMenuBar() {
		//fileMenu.add(load);
		//load.setEnabled(true);
		
		fileMenu.add(save);
		save.setEnabled(false);
		save.setToolTipText("Cannot save data. No data is present.");
		
		fileMenu.add(Import);
		Import.setEnabled(true);
		
		fileMenu.add(Export);
		Export.setEnabled(true);
		Export.setToolTipText("Cannot export data. No data is present.");
		
		menuBar.add(fileMenu);
		editMenu.add(add);
		add.setEnabled(false);
		add.setToolTipText("Cannot add data of selected type.");
		
		editMenu.add(edit);
		edit.setEnabled(false);
		edit.setToolTipText("Cannot edit data. No data is selected.");
		
		editMenu.add(delete);
		delete.setEnabled(false);
		delete.setToolTipText("Cannot delete data. No data is selected.");
		
		editMenu.add(clear);
		clear.setEnabled(false);
		clear.setToolTipText("Cannot clear data. No data is present.");
		
		editMenu.add(clearAll);
		clearAll.setEnabled(false);
		clearAll.setToolTipText("Cannot clear all. No data is present.");
		
		menuBar.add(editMenu);
		displayMenu.add(pieChart);
		pieChart.setEnabled(false);
		pieChart.setToolTipText("Cannot create a Pie Chart. No data is present.");
		
		displayMenu.add(histogram);
		histogram.setEnabled(false);
		histogram.setToolTipText("Cannot create a Histogram. No data is present.");
		
		menuBar.add(displayMenu);
		selectionViewFrame.setJMenuBar(menuBar);
	}
	private void createRadioPanel() {
		GridLayout b= new GridLayout();
		b.setRows(8);
		radioPanel.setLayout(b);
		radioPanel.setPreferredSize(new Dimension(250, 100));
		radioPanel.setBorder(border);
		radioGroup.add(MediaRadioButton);
		radioGroup.add(MoviesRadioButton);
		radioGroup.add(SeriesRadioButton);
		radioGroup.add(EpisodesRadioButton);
		radioGroup.add(MakersRadioButton);
		radioGroup.add(ActorsRadioButton);
		radioGroup.add(DirectorsRadioButton);
		radioGroup.add(ProducerRadioButton);
		radioPanel.add(MediaRadioButton);
		radioPanel.add(MoviesRadioButton);
		radioPanel.add(SeriesRadioButton);
		radioPanel.add(EpisodesRadioButton);
		radioPanel.add(MakersRadioButton);
		radioPanel.add(ActorsRadioButton);
		radioPanel.add(DirectorsRadioButton);
		radioPanel.add(ProducerRadioButton);
		selectionViewFrame.add(radioPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	/**
	 * Creates the general view page
	 * @param model Takes a model object
	 */
	public void setModel(MediaModel model) {
		this.model = model;
		
		if (model != null) {
			model.addActionListener(this);
			JFrame.setDefaultLookAndFeelDecorated(true);
			selectionViewFrame.setSize(500,500);
			selectionViewFrame.setTitle("MDb");
			selectionViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			selectionViewFrame.setLayout(new BorderLayout());
			jScrollPane.add(defaultList);
			border.setTitleJustification(TitledBorder.CENTER);
			border.setTitlePosition(TitledBorder.TOP);
			
			createScrollPanel();
			createRadioPanel();
			createMenuBar();
			MediaController.addSelectionViewActionListeners();
			
			MediaController.addFileViewsActionListeners();
			
			MediaController.addEditAddViewsActionListeners();
			
			selectionViewFrame.setVisible(true);
		}
	}
}
