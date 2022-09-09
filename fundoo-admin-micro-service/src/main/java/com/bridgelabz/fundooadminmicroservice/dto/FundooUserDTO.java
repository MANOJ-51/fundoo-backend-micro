package com.bridgelabz.fundooadminmicroservice.dto;

import lombok.Data;
import java.io.File;
import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating DTO for Fundoo User
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class FundooUserDTO {

	@NotNull(message = "Name Should Not be Null")
	private String name;
	@NotNull(message = "Email Should Not be Null")
	private String email;
	@NotNull(message = "Password Should Not be Null")
	private String password;
	@NotNull(message = "Active Should Not be Null")
	private Boolean isActive;
	@NotNull(message = "Date of Birth Should Not be Null")
	private String dateOfBirth;
	@NotNull(message = "Mobile Number Should Not be Null")
	private String phoneNumber;
}
