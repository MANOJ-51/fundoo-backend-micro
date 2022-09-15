package com.bridgelabz.fundoonotemicroservice.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * Purpose:Creating DTO for Fundoo lable
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class LableDTO {
	@NotNull(message = "lable name should not be null")
	private String lableName;
}
