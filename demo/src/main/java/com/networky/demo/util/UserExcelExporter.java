//package com.networky.demo.util;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
//
//import com.networky.demo.entities.Image;
//import com.networky.demo.entities.Post;
//import com.networky.demo.entities.User;
//
//
//public class UserExcelExporter {
//    private XSSFWorkbook workbook;
//    private XSSFSheet sheet;
//    private List<User> listUsers;
//    
//    private List<Post> listUserPosts;
//    private List<Image> listUserImages;
//    
//     
//    public UserExcelExporter() { }
//    
//    
//
//	public UserExcelExporter(XSSFWorkbook workbook, XSSFSheet sheet, List<User> listUsers, List<Post> listUserPosts,
//			List<Image> listUserImages) {
//		this.workbook = workbook;
//		this.sheet = sheet;
//		this.listUsers = listUsers;
//		this.listUserPosts = listUserPosts;
//		this.listUserImages = listUserImages;
//	}
//
//
//
//	public UserExcelExporter(List<User> listUsers) {
//        this.listUsers = listUsers;
//        workbook = new XSSFWorkbook();
//    }
// 
// 
//    private void writeHeaderLine() {
//        sheet = workbook.createSheet("Users");
//         
//        Row row = sheet.createRow(0);
//         
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(16);
//        style.setFont(font);
//         
//        createCell(row, 0, "User ID", style);      
//        createCell(row, 1, "nome", style);       
//        createCell(row, 2, "cognome", style);    
//        createCell(row, 3, "ddn", style);
//        createCell(row, 4, "country", style);
//         
//    }
//     
//    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
//        if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        }else {
//            cell.setCellValue((String) value);
//        }
//        cell.setCellStyle(style);
//    }
//     
//    private void writeDataLines() {
//        int rowCount = 1;
// 
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setFontHeight(14);
//        style.setFont(font);
//                 
//        for (User user : listUsers) {
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//             
//            createCell(row, columnCount++, user.getId(), style);
//            createCell(row, columnCount++, user.getNome(), style);
//            createCell(row, columnCount++, user.getCognome(), style);
//            createCell(row, columnCount++, user.getDataDiNascita().toString(), style);
//            createCell(row, columnCount++, user.getCountry(), style);
//             
//        }
//    }
//     
//    public void export(HttpServletResponse response) throws IOException {
//    	byte[] contentReturn = null;
//        writeHeaderLine();
//        writeDataLines();
//         
//        OutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);   
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        workbook.write(baos);
//        contentReturn = baos.toByteArray();
//        workbook.close();
//         
//        outputStream.close();
//         
//    }
//    
//    
//}