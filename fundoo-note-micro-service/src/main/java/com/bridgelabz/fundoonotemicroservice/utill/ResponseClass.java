package com.bridgelabz.fundoonotemicroservice.utill;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Purpose:Creating Response class
 * 
 * @author Manoj
 * @Param errorMessage,ErrorMessage,token. Version 1.0
 */
@Data
@AllArgsConstructor
public class ResponseClass {
	private int errorCode;
	private String errorMessage;
	private Object token;

	public ResponseClass() {
	}
}
