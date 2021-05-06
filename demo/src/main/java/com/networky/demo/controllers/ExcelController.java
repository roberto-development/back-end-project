package com.networky.demo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.networky.demo.dtos.ExcelDTO;
import com.networky.demo.services.implementations.ExcelService;
import com.networky.demo.util.ExcelHelper;

@RestController
@RequestMapping("/api")
public class ExcelController extends CrossOriginController {
	
	private ExcelHelper eh;
	
	private ExcelService es;
	
	@Autowired
	public ExcelController(ExcelService es, ExcelHelper eh) {
		this.es = es;
		this.eh = eh;
	}

//	@GetMapping("/excel")
	@RequestMapping(method = RequestMethod.GET, value = "/excel", produces = {"*/*"})
	@ResponseBody
	public ResponseEntity<ExcelDTO> downloadExcel() throws IOException {
		ExcelDTO excelDTO = new ExcelDTO();
		excelDTO = es.load();
		System.out.println(excelDTO.toString());
		return ResponseEntity.ok()
				.body(excelDTO);
	}
	
	
	@CrossOrigin
	@ResponseBody
	@GetMapping(value = "/exportExcel")
	public ResponseEntity<InputStreamResource> exportUserListExcel(HttpServletResponse response) throws IOException {
	        byte[] excelContent = eh.exportToExcelFile();
	    if (excelContent.length != 0) {
	        String fileName = "list-user.xlsx";
	            MediaType mediaType = MediaType.parseMediaType("application/vnd.ms-excel");
	            File file = new File(fileName);
	            FileUtils.writeByteArrayToFile(file, excelContent);
	            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

	            return ResponseEntity.ok()
	                    // Content-Disposition
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
	                    // Content-Type
	                    .contentType(mediaType)
	                    // Contet-Length
	                    .contentLength(file.length()) //
	                        .body(resource);
	        } else {
	        	System.out.println("else di export");
	            return null; // you can return what you want !
	        }

	}

}
