package com.bridgelabz.fundoonotemicroservice.utill;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Purpose:Creating response for user
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
