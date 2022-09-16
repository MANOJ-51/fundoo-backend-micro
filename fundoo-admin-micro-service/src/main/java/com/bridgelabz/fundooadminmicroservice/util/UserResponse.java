package com.bridgelabz.fundooadminmicroservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Purpose:Creating response for file uplode
 * 
 * @author Manoj
 * 
 * Version 1.0
 */
@Data
@AllArgsConstructor
public class UserResponse {
	private int statusCode;
	private String message;
	private Object data;
}
