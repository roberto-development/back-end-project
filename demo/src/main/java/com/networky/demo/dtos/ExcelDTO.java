package com.networky.demo.dtos;

import java.util.Arrays;

import javax.persistence.Lob;

public class ExcelDTO {
	
//	stringa per base64
	@Lob
	private byte[] excelBase64;
	
//	private String typeExcel;
	
	private String fileName;
	

	public ExcelDTO() {
	}

	public ExcelDTO(byte[] excelBase64, String fileName) {
		this.excelBase64 = excelBase64;
		this.fileName = fileName;
	}

	public byte[] getExcelBase64() {
		return excelBase64;
	}

	public void setExcelBase64(byte[] excelBase64) {
		this.excelBase64 = excelBase64;
	}

	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ExcelDTO [excelBase64=" + Arrays.toString(excelBase64) + ", fileName=" + fileName + "]";
	}

	
	
	
	

}
