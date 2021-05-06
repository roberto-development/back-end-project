package com.networky.demo.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;
import com.networky.demo.dtos.UserDTO;
import com.networky.demo.entities.User;
import com.networky.demo.services.interfaces.UserService;
import com.networky.demo.util.UserPDFExporter;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class UserController extends CrossOriginController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
//	   @GetMapping("/users/export/pdf")
//	    public void exportToPDF(HttpServletResponse response) throws org.dom4j.DocumentException, Exception {
//	        response.setContentType("application/pdf");
//	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//	        String currentDateTime = dateFormatter.format(new Date());
//	         
//	        String headerKey = "Content-Disposition";
//	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//	        response.setHeader(headerKey, headerValue);
//	        List<User> listUsers = userService.listAll();
//	         
//	        UserPDFExporter exporter = new UserPDFExporter(listUsers);
//	        exporter.export(response);
//	         
//	    }
	
//	 @GetMapping("/users/export/excel")
//	    public void exportToExcel(HttpServletResponse response) throws IOException {
//		 	
//	        response.setContentType("application/octet-stream");
//	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//	        String currentDateTime = dateFormatter.format(new Date());
//	         
//	        String headerKey = "Content-Disposition";
//	        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//	        response.setHeader(headerKey, headerValue);
//	         
//	        List<User> listUsers = userService.listAll();
//	         
//	        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
//	         
//	        excelExporter.export(response);    
//	    }  
	 
//	 mai get void --> get sempre return oggetto anche vuoto
	 
	 @GetMapping("/users/export/csv")
	    public void exportToCSV(HttpServletResponse response) throws IOException {
	        response.setContentType("text/csv");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<User> listUsers = userService.listAll();
	 
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        String[] csvHeader = {"User ID", "Nome", "Cognome", "Ddn", "Country"};
	        String[] nameMapping = {"id", "nome", "cognome", "dataDiNascita", "country"};
	         
	        csvWriter.writeHeader(csvHeader);
	         
	        for (User user : listUsers) {
	            csvWriter.write(user, nameMapping);
	        }
	         
	        csvWriter.close();
	        
	    }
	 
	 
	 
	


//	@PostMapping("/login")
//	public UserDTO login(@RequestBody AccountDTO accountDTO) {
//		System.out.println(accountDTO.toString());
//		UserDTO userLogin = userService.getUser(accountDTO);
//		return userLogin;
//	}
//	
//	@PostMapping("/getUser")
//	public ResponseEntity<UserDTO> signIn(@RequestBody AccountDTO accountDTO) {
//		ResponseEntity<UserDTO> userDTO = userService.getProfileUser(accountDTO);
////		non restituire dto 
//		return userDTO;
//	}

	@PutMapping("/userUpdate")
	public UserDTO updateUser(@RequestBody UserDTO user) {
		return userService.updateUser(user);

	}
}