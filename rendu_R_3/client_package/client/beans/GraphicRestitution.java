package client.beans;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public abstract class GraphicRestitution extends ApplicationFrame{
	
	protected  LoanRepaymentsPlan loanRepaymentsPlan;
	
	public GraphicRestitution(String title) {
		super(title);
	}
	protected abstract DefaultCategoryDataset createDataset();
}
