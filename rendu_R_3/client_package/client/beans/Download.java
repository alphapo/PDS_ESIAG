package client.beans;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Download {
	private static String FILE = "recapitulatif.pdf";

	public static void pdfVersion(ArrayList<TempoData> data, double capital, double rate, double duration, double insurance) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			// Left
			String message = "Pour emprunt de "+capital+" € avec un taux d'intérêt de "+rate*100+"% et une assurance mensuelle de "+insurance+" € , nous vous proposons le tableau d'amortissement ci-dessous pour la durée de "+duration+" mois";
			Paragraph paragraph1 = new Paragraph(message);
			paragraph1.setAlignment(Element.ALIGN_LEFT);
			
			Paragraph paragraph2 = new Paragraph("Tableau d'amortissement");
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph paragraph3 = new Paragraph(" ");
			paragraph3.setAlignment(Element.ALIGN_CENTER);
			
			document.add(paragraph1);
			document.add(paragraph2);
			document.add(paragraph3);
			
			
			PdfPTable table = new PdfPTable(6); 
			table.addCell("Numéro d'échéance");
			table.addCell("Mensualité");
			table.addCell("Capital Amorti");
			table.addCell("Intérêts");
			table.addCell("Assurance");
			table.addCell("Capital Restant");
			
			for (int i=0; i<data.size(); i++){
				table.addCell(data.get(i).getNumberOfDueDate() );
				table.addCell(Double.toString(data.get(i).getMonthly()) );
				table.addCell(Double.toString(data.get(i).getAmortizedCapital()) );
				table.addCell(Double.toString(data.get(i).getInterest())  );
				table.addCell(Double.toString(data.get(i).getInsurance()) );
				table.addCell(Double.toString(data.get(i).getRemaining()) );
			}
			
			document.add(table);
			document.close();	
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
