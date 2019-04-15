package graphicalDisplay;

import java.awt.Font;
import java.util.Collections;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import common.MediaComparator;
import mediaTypes.Media;
import mediaTypes.Movie;
import mediaTypes.Series;
import people.MediaMaker;

public class StackedBarchart extends JFrame{
	private static final long serialVersionUID = -1907313207525200416L;
	
	
	private static final String MOVIE_ACTING_GROUP = "Movie Acting";
	private static final String MOVIE_DIRECTING_GROUP = "Movie Directing";
	private static final String MOVIE_PRODUCING_GROUP = "Movie Producing";
	private static final String SERIES_ACTING_GROUP = "Series Acting";
	private static final String SERIES_DIRECTING_GROUP = "Series Directing";
	private static final String SERIES_PRODUCING_GROUP = "Series Producing";
	
	/**
	 * Constructs a histogram object
	 * @param title Title of the histogram
	 * @param mediaMaker Mediamaker person
	 */
	public StackedBarchart(String title, MediaMaker mediaMaker){
	   CategoryDataset dataset = createDataset(mediaMaker);
	   JFreeChart chart = createChart(dataset, title);
	   ChartPanel chartPanel = new ChartPanel(chart);
	   chartPanel.setPreferredSize(new java.awt.Dimension(700, 500));
	   setContentPane(chartPanel);
	  // ChartUtilities.write
	}
	/**
	 * Creates a Category Dataset
	 * @param mediaMaker Mediamaker for the dataset
	 * @return Returns the dataset
	 */
	private CategoryDataset createDataset(MediaMaker mediaMaker) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Collections.sort(mediaMaker.getDirectedList(), new MediaComparator(MediaComparator.SORT_BY_YEAR));
		Collections.sort(mediaMaker.getActingList(), new MediaComparator(MediaComparator.SORT_BY_YEAR));
		Collections.sort(mediaMaker.getProducedList(), new MediaComparator(MediaComparator.SORT_BY_YEAR));
		for(int i = mediaMaker.getLowestYear(); i < 2017; i ++){
			dataset.setValue(0.0, StackedBarchart.MOVIE_ACTING_GROUP, Integer.toString(i));
		}
		for(Media m : mediaMaker.getDirectedList()){
			if(m instanceof Movie && m.getStartYear() != -1){
				dataset.setValue(Media.getMediasInYear(m.getStartYear(), mediaMaker.getDirectedList()), StackedBarchart.MOVIE_DIRECTING_GROUP, Integer.toString(m.getStartYear()));
			}else if(m instanceof Series && m.getStartYear() != -1){
				dataset.setValue(Media.getMediasInYear(m.getStartYear(), mediaMaker.getDirectedList()), StackedBarchart.SERIES_DIRECTING_GROUP, Integer.toString(m.getStartYear()));
			}
		}
		for(Media m : mediaMaker.getActingList()){
			if(m instanceof Movie && m.getStartYear() != -1){
				dataset.setValue(Media.getMediasInYear(m.getStartYear(), mediaMaker.getActingList()), StackedBarchart.MOVIE_ACTING_GROUP, Integer.toString(m.getStartYear()));
			}else if(m instanceof Series && m.getStartYear() != -1){
				dataset.setValue(Media.getMediasInYear(m.getStartYear(), mediaMaker.getActingList()), StackedBarchart.SERIES_ACTING_GROUP, Integer.toString(m.getStartYear()));
			}
		}
		for(Media m : mediaMaker.getProducedList()){
			if(m instanceof Movie && m.getStartYear() != -1){
				dataset.setValue(Media.getMediasInYear(m.getStartYear(), mediaMaker.getProducedList()), StackedBarchart.MOVIE_PRODUCING_GROUP, Integer.toString(m.getStartYear()));
			}else if(m instanceof Series && m.getStartYear() != -1){
				dataset.setValue(Media.getMediasInYear(m.getStartYear(), mediaMaker.getProducedList()), StackedBarchart.SERIES_PRODUCING_GROUP, Integer.toString(m.getStartYear()));
			}
		}
		return dataset;
	}
	/**
	 * Creates the chart
	 * @param dataset Takes the set of data
	 * @param title Title of the chart
	 * @return Returns the chart
	 */
	private JFreeChart createChart(CategoryDataset dataset,String title) {
		JFreeChart chart = ChartFactory.createStackedBarChart3D(title, "Content Type", "Years", dataset, PlotOrientation.VERTICAL,
				true, true, false);
		GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
		KeyToGroupMap map = new KeyToGroupMap("g1");
		map.mapKeyToGroup(StackedBarchart.MOVIE_DIRECTING_GROUP, "g1");
		map.mapKeyToGroup(StackedBarchart.SERIES_DIRECTING_GROUP, "g1");
		map.mapKeyToGroup(StackedBarchart.MOVIE_ACTING_GROUP, "g1");
		map.mapKeyToGroup(StackedBarchart.SERIES_ACTING_GROUP, "g1");
		map.mapKeyToGroup(StackedBarchart.MOVIE_PRODUCING_GROUP, "g1");
		map.mapKeyToGroup(StackedBarchart.SERIES_PRODUCING_GROUP, "g1");
		renderer.setItemMargin(0.0);
		renderer.setRenderAsPercentages(true);
		renderer.setSeriesToGroupMap(map); 
		SubCategoryAxis domainAxis = new SubCategoryAxis("Years");
		Font font3 = new Font("Dialog", Font.PLAIN, 6); 
		domainAxis.setCategoryMargin(0.0);
		domainAxis.setTickLabelFont(font3);
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		plot.setDomainAxis(domainAxis);
		plot.setRenderer(renderer);
		return chart;
	}
}
