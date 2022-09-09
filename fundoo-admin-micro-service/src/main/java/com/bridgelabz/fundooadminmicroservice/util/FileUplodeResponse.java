package com.bridgelabz.fundooadminmicroservice.util;

import lombok.Data;

/**
 * Purpose:Creating response for file uplode
 * 
 * @author Manoj
 * 
 * Version 1.0
 */
@Data
public class FileUplodeResponse {
	private String fileName;
	private String downlodeUrl;
	private Long size;
}
