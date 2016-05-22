package client.beans;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class ShowGraphicBar3D extends GraphicRestitution{
		
	public ShowGraphicBar3D(final LoanRepaymentsPlan loanRepaymentPlan,  String applicationTitle , String chartTitle )
	{
		super(applicationTitle);
		super.loanRepaymentsPlan = loanRepaymentPlan;
		JFreeChart lineChart = ChartFactory.createBarChart3D(
				chartTitle,
				"Echéances","Montant en Euro",
				createDataset(),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new Dimension( 660 , 367 ) );
		
		setContentPane(chartPanel);
		this.pack();
		RefineryUtilities.positionFrameOnScreen(this, RIGHT_ALIGNMENT, TOP_ALIGNMENT);
		this.setVisible(true);
	}

	protected DefaultCategoryDataset createDataset()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

		for(int i = 0; i<(loanRepaymentsPlan.getData().size()-1); i++){
			dataset.addValue( loanRepaymentsPlan.getData().get(i).getAmortizedCapital() , "Capital Amorti", (loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString() );
			dataset.addValue( loanRepaymentsPlan.getData().get(i).getInterest(),       "Interets",       (loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString() );
			dataset.addValue( loanRepaymentsPlan.getData().get(i).getRemaining(),      "Capital restant",(loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString() );
			dataset.addValue( loanRepaymentsPlan.getData().get(i).getInsurance() ,     "Assurance",      (loanRepaymentsPlan.getData().get(i).getNumberOfDueDate()).toString() );
		}
		return dataset;
	}
}
