package client.beans;


import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;



public class ShowGraphicLine extends GraphicRestitution{
		
	public ShowGraphicLine(final LoanRepaymentsPlan loanRepaymentsPlan, String applicationTitle , String chartTitle )
	{
		super(applicationTitle);	
		super.loanRepaymentsPlan = loanRepaymentsPlan;

		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"Echéances","Montant en Euro",
				createDataset(),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize( new Dimension( 660 , 367 ) );
		CategoryPlot plot = lineChart.getCategoryPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setBaseShapesVisible(true);
		setContentPane( chartPanel );
		this.pack( );
		RefineryUtilities.positionFrameOnScreen(this, LEFT_ALIGNMENT, TOP_ALIGNMENT);
		this.setVisible(true);
	}

	protected DefaultCategoryDataset createDataset( )
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(int i = 0; i<(loanRepaymentsPlan.getData().size()-1); i++){
			dataset.addValue( loanRepaymentsPlan.getData().get(i).getAmortizedCapital() , "Capital Amorti", (loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString() );
			dataset.addValue( loanRepaymentsPlan.getData().get(i).getInterest(), "Interets",  (loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString());
         	dataset.addValue(loanRepaymentsPlan.getData().get(i).getRemaining(), "Capital restant",   (loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString() );
		}
		return dataset;
	}
}
