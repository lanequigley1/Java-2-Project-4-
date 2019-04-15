package graphicalDisplay;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileViews extends JFrame{
	private static final long serialVersionUID = 7431719392802831383L;
	public static JFileChooser importFileChooser = new JFileChooser();
	/**
	 * Generates a file chooser for importing
	 * @return Returns an array of files of each file the user selected
	 */
	public static File[] importFileChooser(){
		JFrame frame = new JFrame();
		importFileChooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT file","txt");
		importFileChooser.setFileFilter(filter);
		importFileChooser.showOpenDialog(frame);
		
		File[] fileArray = importFileChooser.getSelectedFiles();
		importFileChooser.setVisible(true);
		return fileArray;
	}
	public static JFileChooser saveFileChooser = new JFileChooser();
	public static File[] exportFileChooser(){
		JFrame frame = new JFrame();
		saveFileChooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT file","txt");
		saveFileChooser.setFileFilter(filter);
		saveFileChooser.showOpenDialog(frame);
		
		File[] fileArray = saveFileChooser.getSelectedFiles();
		saveFileChooser.setVisible(true);
		return fileArray;
	}
	protected static JRadioButton MovieFileTypeRadioButton= new JRadioButton("Movies");
	protected static JRadioButton SeriesFileTypeButton= new JRadioButton("Series");
	protected static JRadioButton ActorFileTypeRadioButton= new JRadioButton("Actor");
	protected static JRadioButton DirectorFileTypeRadioButton= new JRadioButton("Director");
	protected static JRadioButton ProducerFileTypeRadioButton= new JRadioButton("Producer");
	protected static JButton fileTypeConfirmButton = new JButton("Ok");
	protected static ButtonGroup fileTypeRadioButtonGroup = new ButtonGroup();
	protected static JFrame importFrame = new JFrame();
	
	public static void fileTypeImporting(){
		importFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel pane = new JPanel();
		GridLayout b = new GridLayout();
		b.setRows(4);
		pane.setLayout(b);
		pane.setPreferredSize(new Dimension(250, 100));
		fileTypeRadioButtonGroup.add(MovieFileTypeRadioButton);
		fileTypeRadioButtonGroup.add(SeriesFileTypeButton);
		fileTypeRadioButtonGroup.add(ActorFileTypeRadioButton);
		fileTypeRadioButtonGroup.add(DirectorFileTypeRadioButton);
		fileTypeRadioButtonGroup.add(ProducerFileTypeRadioButton);
		
		
		pane.add(MovieFileTypeRadioButton);
		pane.add(SeriesFileTypeButton);
		pane.add(ActorFileTypeRadioButton);
		pane.add(DirectorFileTypeRadioButton);
		pane.add(ProducerFileTypeRadioButton);
		
		pane.add(fileTypeConfirmButton);
		
		importFrame.add(pane);
		importFrame.setTitle("Import File Type");
		importFrame.pack();
		importFrame.setVisible(true);
	}
	
	public static JFrame exportFrame = new JFrame();
	public static JButton exportButton = new JButton();
	
	public static void fileTypeExporting(){
		exportFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel pane = new JPanel();
		GridLayout b = new GridLayout();
		b.setRows(4);
		pane.setLayout(b);
		pane.setPreferredSize(new Dimension(250, 100));
		exportButton.setText("Ok");
		fileTypeRadioButtonGroup.add(MovieFileTypeRadioButton);
		fileTypeRadioButtonGroup.add(SeriesFileTypeButton);
		fileTypeRadioButtonGroup.add(ActorFileTypeRadioButton);
		fileTypeRadioButtonGroup.add(DirectorFileTypeRadioButton);
		fileTypeRadioButtonGroup.add(ProducerFileTypeRadioButton);
		
		
		pane.add(MovieFileTypeRadioButton);
		pane.add(SeriesFileTypeButton);
		pane.add(ActorFileTypeRadioButton);
		pane.add(DirectorFileTypeRadioButton);
		pane.add(ProducerFileTypeRadioButton);
		pane.add(exportButton);
		exportFrame.add(pane);
		exportFrame.setTitle("Export File Type");
		exportFrame.pack();
		exportFrame.setVisible(true);
	}
	/**
	 * Confirms the user wants to delete the object
	 * @return Returns a value depending on if the user hit OK or no
	 */
	public static int deleteConfirmation(){
		JFrame frame = new JFrame();
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(frame,
		"Are you sure you want to delete this permanently?", 
		"Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
		System.out.println(n);
		//YEs is ZERO
		//NO is 1
		return n;
	}
}
