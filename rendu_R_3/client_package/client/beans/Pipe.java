package client.beans;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Pipe {
	double amount ;
	double rateByYear;
	double duration;
	double insuranceByMonth;
	Frequency frequency;
	int reimbursementType;
	/*	
	 *  1 - for in fine repayments
	 *  2 - for constant amortization 
	 *  3 - for constant annuity
	 */

	public Pipe(double amount, double rateByYear, double duration, double insuranceByMonth, Frequency frequency, int reimbursementType) {
		this.amount = amount;
		this.rateByYear = rateByYear;
		this.duration = duration;
		this.insuranceByMonth = insuranceByMonth;
		this.frequency = frequency;
		this.reimbursementType = reimbursementType;
		
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		/******************** calculate loan repayments plan *************************/
		LoanRepaymentsPlan loanRepaymentPlan = new LoanRepaymentsPlan(amount, rateByYear, duration, insuranceByMonth, reimbursementType, frequency);
		loanRepaymentPlan.fillArray();
		Summary recap = new Summary(loanRepaymentPlan);

		JPanel pane = new JPanel();
		JPanel paneNorth = new JPanel();
		JPanel paneSouth = new JPanel();
		JPanel paneCenter = new JPanel();

		pane.setLayout(new BorderLayout());

		JLabel capitalLabel = new JLabel("Capital : "+loanRepaymentPlan.getCapital()+"€");
		JLabel rateLabel = new JLabel("Taux d'intérêt : "+loanRepaymentPlan.getRate()*100+"%");
		JLabel durationLabel = new JLabel("Durée : "+loanRepaymentPlan.getDuration()+" mois");
		
		capitalLabel.setAlignmentX((float) 0.5);
		rateLabel.setAlignmentX((float) 0.5);
		durationLabel.setAlignmentX((float) 0.5);

		BoxLayout boxLayout = new BoxLayout(paneNorth, BoxLayout.Y_AXIS); // top to bottom
		JLabel title = new JLabel("RAPPEL DES PARAMETRES DE LA SIMULATION");
		title.setAlignmentX((float) 0.5);
		title.setFont(new Font("Italic", Font.BOLD, 20));
		
		JLabel array = new JLabel("Tableau d'amortissement");
		array.setFont(new Font("Serif", Font.BOLD, 16));
		array.setAlignmentX((float) 0.5);
		paneNorth.setLayout(boxLayout);
		paneNorth.add(new JLabel("  "));
		paneNorth.add(title);
		paneNorth.add(new JLabel("  "));
		paneNorth.add(capitalLabel);
		paneNorth.add(rateLabel);
		paneNorth.add(durationLabel);
		paneNorth.add(new JLabel("  "));
		paneNorth.add(array);
		pane.add("North", paneNorth);


		/******************** add loan repayments plan *********************/
		JTable arrayFrame = new JTable(new ShowArrayRestitution(loanRepaymentPlan));
		paneCenter.add(new JScrollPane(arrayFrame));
		pane.add("Center", paneCenter);

		JButton printButton = new JButton("Imprimer le récapitulatif");
		printButton.addMouseListener(new MouseAdapter()
		{ public void mouseClicked(MouseEvent event) {
			recap.printWithDevice();
		}
		}); 

		JButton downloadButton = new JButton("Télécharger le récapitulatif");
		downloadButton.addMouseListener(new MouseAdapter()
		{ public void mouseClicked(MouseEvent event) {
			recap.downloadPdfVersion();
		}
		});

		JButton diagramBar = new JButton("Diagramme en bande");
		diagramBar.addMouseListener(new MouseAdapter()
		{ public void mouseClicked(MouseEvent event) {
			new ShowGraphicBar3D(loanRepaymentPlan, "Graphique" ,"Restitution graphique de la simulation");
		}
		});

		JButton diagramLine = new JButton("Diagramme en ligne");
		diagramLine.addMouseListener(new MouseAdapter()
		{ public void mouseClicked(MouseEvent event) {
			new ShowGraphicLine(loanRepaymentPlan, "Graphique" ,"Restitution graphique de la simulation"); 
		}
		});
		
		JButton printArray = new JButton("Imprimer le tableau");
		printArray.addMouseListener(new MouseAdapter()
		{ public void mouseClicked(MouseEvent event) {
			try {
				arrayFrame.print();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});

		paneSouth.setLayout(new FlowLayout());
		paneSouth.add(printArray);
		paneSouth.add(printButton);
		paneSouth.add(downloadButton);
		paneSouth.add(diagramBar);
		paneSouth.add(diagramLine);
		

		pane.add("South",paneSouth);

		jframe.setContentPane(pane);
		jframe.pack();
		jframe.setVisible(true);
	}

}