package com.bridgelabz.fundoonotemicroservice.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Purpose:Creating DTO for Fundoo note
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class NoteDTO {

	@NotNull(message = "Title Should Not be Null")
	private String title;
	@NotNull(message = "description Should Not be Null")
	private String description;
	@NotNull(message = "color Should Not be Null")
	private String color;
	@NotNull(message = "email Should Not be Null")
	private String email;
}
