package client.beans;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class DownloadableDocument {
	private String ROOT = "Derniere_Simulation";
	private String FILE;
	private String currentPath;

	public DownloadableDocument() {
		try {
			currentPath = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		do {
			FILE = JOptionPane.showInputDialog(null, "Donnez un nom au recapitulatif que vous souhaitez télécharger s'il vous plaît");
		}while (FILE.isEmpty());
	}

	public  void pdfVersion(LoanRepaymentPlan loanRepaymentsPlan) {
		try {

			if(! FILE.endsWith(".pdf") )
				FILE = FILE+".pdf";

			new File(ROOT).mkdirs();

			cleanDirectory();

			String relativePath = ROOT+"\\"+FILE;

			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(relativePath));
			document.open();

			// recover current date only
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			String Date = dateFormat.format(new Date());
			String[] temporary_array = Date.split(" ");
			String currentDate = temporary_array[0];

			// Set color and police 
			Font f1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 30);
			f1.setColor(BaseColor.BLUE);
			
			Font f2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.UNDERLINE);
			f2.setColor(BaseColor.BLUE);
			
			Paragraph paragraph0 = new Paragraph("Récapitulatif", f1);
			paragraph0.setAlignment(Element.ALIGN_CENTER);
			
			
			// dynamic and customized message
			String message = "Suite à votre simulation du "+currentDate+" pour emprunter "+loanRepaymentsPlan.getCapital()+" € avec un taux d'intérêt de "+loanRepaymentsPlan.getRate()*100+"% et une assurance mensuelle de "+loanRepaymentsPlan.getInsurance()+" € , nous vous avons proposé le tableau d'amortissement ci-dessous pour la durée de "+loanRepaymentsPlan.getDuration()+" mois avec un remboursement "+frequencyToString(loanRepaymentsPlan.getFrequency())+".";
			Paragraph paragraph1 = new Paragraph(message);
			paragraph1.setAlignment(Element.ALIGN_LEFT);
			
			Chunk titleOfArray = new Chunk("Tableau d'amortissement", f2);
			Paragraph paragraph2 = new Paragraph(titleOfArray);
			paragraph2.setAlignment(Element.ALIGN_CENTER);

			Paragraph lineBreak = new Paragraph(" ");
			lineBreak.setAlignment(Element.ALIGN_CENTER);

			document.add(paragraph0);
			document.add(lineBreak);
			document.add(paragraph1);
			document.add(paragraph2);
			document.add(lineBreak);


			PdfPTable table = new PdfPTable(6); 
			table.addCell("Numéro d'échéance");
			table.addCell("Mensualité");
			table.addCell("Capital Amorti");
			table.addCell("Intérêts");
			table.addCell("Assurance");
			table.addCell("Capital Restant");

			for (int i=0; i<loanRepaymentsPlan.getData().size(); i++){
				table.addCell(loanRepaymentsPlan.getData().get(i).getNumberOfDueDate() );
				table.addCell(Double.toString(loanRepaymentsPlan.getData().get(i).getMonthly()) );
				table.addCell(Double.toString(loanRepaymentsPlan.getData().get(i).getAmortizedCapital()) );
				table.addCell(Double.toString(loanRepaymentsPlan.getData().get(i).getInterest())  );
				table.addCell(Double.toString(loanRepaymentsPlan.getData().get(i).getInsurance()) );
				table.addCell(Double.toString(loanRepaymentsPlan.getData().get(i).getRemaining()) );
			}

			document.add(table);
			document.close();
			
			
//			String forInformation = "le fichier téléchargé \'"+FILE+"\' se trouve à l'emplacement suivant \'"+currentPath+"\'"+ROOT;
//			JOptionPane.showMessageDialog(null, forInformation , "Information", 1);

			// and open file for user 
			Desktop.getDesktop().open(new File(relativePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String frequencyToString(Frequency Frequency){
		switch(Frequency){
		case Yearly:	 return "annuel";
		case Monthly:	return "mensuel";
		default:	return null;
		}
	}
	private void cleanDirectory(){
		Path path = Paths.get(ROOT);
		try {
			Files.newDirectoryStream(path).forEach( file -> {
				try { Files.delete( file ); }
				catch ( IOException e ) { throw new UncheckedIOException(e); }
			} );
		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
	}


}
