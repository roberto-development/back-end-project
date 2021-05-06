package com.networky.demo.services.implementations;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networky.demo.dtos.ExcelDTO;
import com.networky.demo.entities.User;
import com.networky.demo.repository.UserRepository;
import com.networky.demo.util.ExcelHelper;

@Service
public class ExcelService {
	
	@Autowired
	public ExcelHelper eh;
	
  @Autowired
  public UserRepository userRepository;

  public ExcelDTO load() {
    List<User> listUsers = userRepository.findAll();

    ExcelDTO excelDTO = new ExcelDTO();
    
    byte[] excelFileBase64 = Base64.getEncoder().encode(eh.tutorialsToExcel(listUsers));
//    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
    excelDTO.setExcelBase64(excelFileBase64);
    excelDTO.setFileName("demo");
    System.out.println(excelDTO.toString());
    return excelDTO;
  }
  
  
  
//  public ExcelDTO loadFileExcel() throws IOException {
//	  List<User> listUsers = userRepository.findAll();
//
//	    ExcelDTO excelDTO = new ExcelDTO();
//	    
//	    byte[] excelFileBase64 = Base64.getEncoder().encode(eh.export(listUsers));
////	    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
//	    excelDTO.setExcelBase64(excelFileBase64);
//	    excelDTO.setFileName("demo");
//	    System.out.println(excelDTO.toString());
//	    return excelDTO;
//  }

}