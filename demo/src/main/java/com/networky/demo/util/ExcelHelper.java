package com.networky.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.networky.demo.entities.User;
import com.networky.demo.services.interfaces.UserService;

@Component
public class ExcelHelper {
	
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Id", "Nome", "Cognome", "Data di Nascita", "Country" };
  static String SHEET = "Users";
  
  private XSSFWorkbook workbook  = new XSSFWorkbook();
  private XSSFSheet sheet = null;
  private List<User> listUsers;

  @Autowired
  private UserService userService;
  
  public ExcelHelper() {
}

public ExcelHelper(XSSFWorkbook workbook, XSSFSheet sheet, List<User> listUsers) {
	this.workbook = new XSSFWorkbook();
//	this.sheet = sheet;
	this.listUsers = listUsers;
}

public byte[] tutorialsToExcel(List<User> users) {

	  byte[] excelFile = null;
	  
    try  {
    		
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	sheet = workbook.createSheet(SHEET);

      // Header
    	
    	Row row = sheet.createRow(0);
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 1, "User ID", style);      
        createCell(row, 2, "nome", style);       
        createCell(row, 3, "cognome", style);    
        createCell(row, 4, "ddn", style);
        createCell(row, 5, "country", style);
    	
      workbook.write(out);
      excelFile = out.toByteArray();
      return excelFile;
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    } 
}

private void createCell(Row row, int columnCount, Object value, CellStyle style) {
    sheet.autoSizeColumn(columnCount);
    Cell cell = row.createCell(columnCount);
    if (value instanceof Integer) {
        cell.setCellValue((Integer) value);
    } else if (value instanceof Boolean) {
        cell.setCellValue((Boolean) value);
    }else {
        cell.setCellValue((String) value);
    }
    cell.setCellStyle(style);
}



public byte[] exportToExcelFile() throws IOException {
	List<User> listUsers = userService.listAll();

  XSSFWorkbook workbook = new XSSFWorkbook(); 
  XSSFSheet sheet = workbook.createSheet("Employee");
  XSSFRow row = sheet.createRow(1);

// create style for header cells
    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setFontName("Arial");
    style.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
//    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//    font.setColor(HSSFColor.WHITE.index);
    style.setFont(font);

  // create header row
  XSSFRow header = sheet.createRow(0);

    header.createCell(0).setCellValue("id");
    header.getCell(0).setCellStyle(style);

    header.createCell(1).setCellValue("Nome");
    header.getCell(1).setCellStyle(style);

    header.createCell(2).setCellValue("Cognome");
    header.getCell(2).setCellStyle(style);

    header.createCell(3).setCellValue("Data Di Nascita");
    header.getCell(3).setCellStyle(style);
    
    header.createCell(4).setCellValue("Country");
    header.getCell(4).setCellStyle(style);
    
    int rowCount = 1;

    for (User user : listUsers) {
        XSSFRow  aRow = sheet.createRow(rowCount++);
        aRow.createCell(0).setCellValue(user.getId());
        aRow.createCell(1).setCellValue(user.getNome());
        aRow.createCell(2).setCellValue(user.getCognome());
        aRow.createCell(3).setCellValue(user.getDataDiNascita());
        aRow.createCell(4).setCellValue(user.getCountry());
        sheet.autoSizeColumn(rowCount);
    }
    

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
        workbook.write(bos);
    } finally {
        bos.close();
    }
    byte[] bytes = bos.toByteArray();
   // FileOutputStream out = new FileOutputStream(new File("employee.xlsx"));
     //workbook.write(out);
      //out.close();
      System.out.println("exceldatabase.xlsx written successfully");
    return bytes;
}
}
  
//  
//  private void writeHeaderLine() {
//	  workbook = new XSSFWorkbook();
//      sheet = workbook.createSheet(SHEET);
//       
//      Row row = sheet.createRow(0);
//       
//      CellStyle style = workbook.createCellStyle();
//      XSSFFont font = workbook.createFont();
//      font.setBold(true);
//      font.setFontHeight(16);
//      style.setFont(font);
//       
//      createCell(row, 0, "User ID", style);      
//      createCell(row, 1, "nome", style);       
//      createCell(row, 2, "cognome", style);    
//      createCell(row, 3, "ddn", style);
//      createCell(row, 4, "country", style);
//       
//  }
//   
//  private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//      sheet.autoSizeColumn(columnCount);
//      Cell cell = row.createCell(columnCount);
//      if (value instanceof Integer) {
//          cell.setCellValue((Integer) value);
//      } else if (value instanceof Boolean) {
//          cell.setCellValue((Boolean) value);
//      }else {
//          cell.setCellValue((String) value);
//      }
//      cell.setCellStyle(style);
//  }
//   
//  private void writeDataLines() {
//      int rowCount = 1;
//
//      CellStyle style = workbook.createCellStyle();
//      XSSFFont font = workbook.createFont();
//      font.setFontHeight(14);
//      style.setFont(font);
//               
//      for (User user : listUsers) {
//          Row row = sheet.createRow(rowCount++);
//          int columnCount = 0;
//           
//          createCell(row, columnCount++, user.getId(), style);
//          createCell(row, columnCount++, user.getNome(), style);
//          createCell(row, columnCount++, user.getCognome(), style);
//          createCell(row, columnCount++, user.getDataDiNascita().toString(), style);
//          createCell(row, columnCount++, user.getCountry(), style);
//           
//      }
//  }
//  
//  
//  public byte[] export(List<User> listUsers) throws IOException {
//  	byte[] contentReturn = null;
//      writeHeaderLine();
//      writeDataLines();
//       
////      OutputStream outputStream = response.getOutputStream();
////      workbook.write(outputStream);   
//      
//      ByteArrayOutputStream baos = new ByteArrayOutputStream();
//      workbook.write(baos);
//      contentReturn = baos.toByteArray();
//      workbook.close();
//      return contentReturn;
//       
//  }
  
