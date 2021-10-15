package com.example.demo.export;
 
import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Ordering;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 
 
public class OrderPDFExporter {
    private List<Ordering> listOrder;
     
    public OrderPDFExporter(List<Ordering> listOrder) {
        this.listOrder = listOrder;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Order ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("ZIP", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Telphone", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("SP note", font));
        table.addCell(cell);
            
    }
     
    private void writeTableData(PdfPTable table) {
        for (Ordering ordering : listOrder) {
            table.addCell(String.valueOf(ordering.getOid()));
            table.addCell(ordering.getZip());
            table.addCell(ordering.getTel());
            table.addCell(ordering.getAddress());
            table.addCell(ordering.getSpecial());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Shipping Details", font);
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