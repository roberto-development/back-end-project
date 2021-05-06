package com.networky.demo.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.networky.demo.entities.User;


public class UserPDFExporter {
    private List<User> listUsers;
     
    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("User ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("NOME", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("COGNOME", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("DATA DI NASCITA", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("COUNTRY", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (User user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getNome());
            table.addCell(user.getCognome());
            table.addCell(user.getDataDiNascita());
            table.addCell(user.getCountry());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException, Exception {
        Document document = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}