package com.example.demo.export;


import java.util.List;

import com.example.demo.model.Payment;
import java.awt.Color;
import java.io.IOException;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 

public class PaymentPDFExporter {
	private List<Payment> listPayments;
	
	public PaymentPDFExporter(List<Payment> listPayments) {
        this.listPayments = listPayments;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Payment ID/Invoice No", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Customer Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Bank", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);       
    }
    
    private void writeTableData(PdfPTable table) {
        for (Payment payment : listPayments) {
            table.addCell(String.valueOf(payment.getPid()));
            table.addCell(payment.getName());
            table.addCell(String.valueOf(payment.getDate()));
            table.addCell(payment.getBank());
            table.addCell(String.valueOf(payment.getAmount()));
        }
    }
    
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Monthly Income", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }

}
