package com.bridgelabz.fundoonotemicroservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Purpose:Creating Custom Exception class
 * 
 * @author Manoj
 * @Param StatusCode ,StatusMessage Version 1.0
 */
@ResponseStatus
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CustomExceptions extends RuntimeException {
	private int statusCode;
	private String statusMessage;
}
