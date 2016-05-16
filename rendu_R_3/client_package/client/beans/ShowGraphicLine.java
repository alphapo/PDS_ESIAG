package client.beans;


import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class ShowGraphicLine extends ApplicationFrame{
	
	private ArrayList<TempoData> data = new ArrayList<TempoData>();
	
	public ShowGraphicLine(final ArrayList<TempoData> data, String applicationTitle , String chartTitle )
	{
		super(applicationTitle);
		
		this.data = data;
		
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"Echéances","Montant en Euro",
				createDataset(),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new Dimension( 860 , 467 ) );
		CategoryPlot plot = lineChart.getCategoryPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setBaseShapesVisible(true);
		setContentPane( chartPanel );
		this.pack( );
		RefineryUtilities.centerFrameOnScreen( this );
		this.setVisible( true );
	}

	private DefaultCategoryDataset createDataset( )
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

		for(int i = 0; i<(data.size()-1); i++){
			dataset.addValue( data.get(i).getAmortizedCapital() , "Capital Amorti", (data.get(i).getNumberOfDueDate()).toString() );
			dataset.addValue( data.get(i).getInterest(), "Interets",  (data.get(i).getNumberOfDueDate()).toString());
         	dataset.addValue(data.get(i).getRemaining(), "Capital restant",   (data.get(i).getNumberOfDueDate()).toString() );
			//    	  dataset.addValue(data.get(i).getInsurance() , "Assurance", (data.get(i).getNumberOfDueDate()).toString() );
		}
		return dataset;
	}
}
