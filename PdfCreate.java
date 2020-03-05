package com.cg.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.model.PolicyDetails;
import com.cg.utility.LoggerUtility;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCreate {
	public int create(PolicyDetails policyDetails) throws IOException {
		String imageFile = "logo.jpg";
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		Logger logger = LoggerUtility.getLogger();
		try {

			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\" + policyDetails.getInsuredName()
					+ "_" + policyDetails.getPolicy().getPolicyNumber() + ".pdf"));
			doc.open();
			// Anchor at = new Anchor("Policy Details");

			//Image image = Image.getInstance(imageFile);
			//image.setAbsolutePosition(36, 400);
			//doc.add(image);
			// ImageData data = ImageDataFactory.create(imageFile);

			Paragraph para = new Paragraph("INSURER'S CHOICE INSURANCE");
			para.setAlignment(Paragraph.ALIGN_CENTER);
			para.setSpacingBefore(50);
			// para1.add(at);
			doc.add(para);

			Paragraph para1 = new Paragraph("POLICY DETAILS");
			para1.setAlignment(Paragraph.ALIGN_CENTER);
			//para1.setSpacingBefore(50);
			// para1.add(at);
			doc.add(para1);
			/*
			 * Rectangle rect= new Rectangle(36,108); rect.setBorder(Rectangle.BOX);
			 * rect.setBorderWidth(2); doc.add(rect);
			 */
			/*
			 * Chapter chap = new Chapter(1); Section sec =
			 * chap.addSection("SEction local");
			 */
			
			PdfPTable table = new PdfPTable(2);
			float[] columnWidths = new float[] { 40f, 60f };
			table.setWidths(columnWidths);
			table.setSpacingBefore(50);
			table.setSpacingAfter(70);
			/*
			 * PdfPCell c1 = new PdfPCell(new Phrase("username")); table.addCell(c1);
			 * PdfPCell c2 = new PdfPCell(new Phrase("password")); table.addCell(c2);
			 * PdfPCell c3 = new PdfPCell(new Phrase("rolecode")); table.addCell(c3);
			 */
			PdfPCell cell = new PdfPCell(new Phrase("Policy Number"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getPolicy().getPolicyNumber());
			cell = new PdfPCell(new Phrase("Account Number"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getPolicy().getAccountNumber());
			cell = new PdfPCell(new Phrase("Insured Name"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getInsuredName());
			cell = new PdfPCell(new Phrase("Street"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getInsuredStreet());
			cell = new PdfPCell(new Phrase("City"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getInsuredCity());
			cell = new PdfPCell(new Phrase("State"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getInsuredState());
			cell = new PdfPCell(new Phrase("ZIP"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getInsuredZip());
			cell = new PdfPCell(new Phrase("Business Segment"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getBusinessSegment());

			List<String> questions = policyDetails.getQuestions();
			List<String> answers = policyDetails.getAnswers();

			for (int i = 0; i < questions.size(); i++) {
				cell = new PdfPCell(new Phrase("" + questions.get(i)));
				cell.setBackgroundColor(new BaseColor(255, 189, 242));
				table.addCell(cell);
				table.addCell("" + answers.get(i));
			}

			cell = new PdfPCell(new Phrase("Premium Amount"));
			cell.setBackgroundColor(new BaseColor(255, 189, 242));
			table.addCell(cell);
			table.addCell("" + policyDetails.getPolicy().getPolicyPremium());
			doc.add(table);

			
			Paragraph para2 = new Paragraph("©2020, Insurer's Choice ");
			para2.setSpacingBefore(100);
			para2.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(para2);
			doc.close();
			logger.info("Pdf Created for " + policyDetails.getPolicy().getPolicyNumber() + " policy number");
			return 1;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
}
