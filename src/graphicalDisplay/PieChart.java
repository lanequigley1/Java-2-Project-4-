/**
 * This class handles the creation of pie charts for the media makers graphical information.
 * Code credit: https://www.youtube.com/watch?v=E8jPNKxOk50
 * Libraries Used: JCommons, JFreeChart
 */
package graphicalDisplay;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieChart extends JFrame{
	private static final long serialVersionUID = 3148120168977629937L;
	
	/**
	 * Constructor for the pie chart
	 * @param appTitle Title of the app
	 * @param chartTitle Chart title
	 * @param actingMovieCredit How many credits
	 * @param actingSeriesCredit How many credits
	 * @param directingMovieCredit How many credits
	 * @param directingSeriesCredit How many credits
	 * @param producingMovieCredit How many credits
	 * @param producingSeriesCredit How many credits
	 */
	public PieChart(String appTitle, String chartTitle, int actingMovieCredit, int actingSeriesCredit, 
			int directingMovieCredit, int directingSeriesCredit, int producingMovieCredit, int producingSeriesCredit){
		
		PieDataset pie = createDataset(actingMovieCredit, actingSeriesCredit, directingMovieCredit, 
				directingSeriesCredit, producingMovieCredit, producingSeriesCredit);
		JFreeChart Chart = createChart(pie, chartTitle);
		ChartPanel chartPanel = new ChartPanel(Chart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		setContentPane(chartPanel);
	}
	/**
	 * Creates a pie dataset
	 * @param actingMovieCredit How many movies a person has acted in
	 * @param actingSeriesCredit How many series a person has acted in 
	 * @param directingMovieCredit How many movies directed
	 * @param directingSeriesCredit How many series directed
	 * @param producingMovieCredit How many movies produced
	 * @param producingSeriesCredit  How many series produced
	 * @return Returns a pieset with the given values and titles
	 */
	private PieDataset createDataset(int actingMovieCredit, int actingSeriesCredit, 
			int directingMovieCredit, int directingSeriesCredit, int producingMovieCredit, int producingSeriesCredit) {
		DefaultPieDataset pieSet = new DefaultPieDataset();
		pieSet.setValue("Movies Acted In", actingMovieCredit);
		pieSet.setValue("Series Acted In", actingSeriesCredit);
		pieSet.setValue("Movies Directed", directingMovieCredit);
		pieSet.setValue("Series Directed", directingSeriesCredit);
		pieSet.setValue("Movies Produced", producingMovieCredit);
		pieSet.setValue("Series Produced", producingSeriesCredit);
		return pieSet;
	}
	/**
	 * Creates a JChart with given customizations
	 * @param pieSet Takes a pieset used for display
	 * @param chartTitle The title of the chart
	 * @return Returns a JChart
	 */ 
	private JFreeChart createChart(PieDataset pieSet, String chartTitle) {
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, pieSet, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(0.0);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5F);
		return chart;
	}
}
