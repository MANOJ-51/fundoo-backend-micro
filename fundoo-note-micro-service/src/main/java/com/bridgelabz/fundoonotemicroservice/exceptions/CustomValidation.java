package com.bridgelabz.fundoonotemicroservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Purpose:Creating Custom validation class
 * 
 * @author Manoj
 * @Param errorCode ,errorMessage Version 1.0
 */
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomValidation extends RuntimeException {
	private int errorCode;
	private String errorMessage;

	public CustomValidation() {
	}
}
