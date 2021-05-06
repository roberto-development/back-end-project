package com.networky.demo.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.networky.demo.entities.User;
import com.networky.demo.services.interfaces.UserService;
import com.networky.demo.util.UserPDFExporter;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins ="*" , allowedHeaders = "*" )
public class PdfController extends CrossOriginController {

	private UserService userService;

	@Autowired
	public PdfController(UserService userService) {
		this.userService = userService;
	}
	
	
	   @GetMapping("/users/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, Exception {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        List<User> listUsers = userService.listAll();
	         
	        UserPDFExporter exporter = new UserPDFExporter(listUsers);
	        exporter.export(response);
	         
	    }
}

