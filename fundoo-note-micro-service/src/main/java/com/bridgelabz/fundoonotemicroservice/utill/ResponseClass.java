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
    private Object user;
	public ResponseClass(int errorCode, String errorMessage, Object token) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.token = token;
	}
	public ResponseClass() {
		super();
	}
}
