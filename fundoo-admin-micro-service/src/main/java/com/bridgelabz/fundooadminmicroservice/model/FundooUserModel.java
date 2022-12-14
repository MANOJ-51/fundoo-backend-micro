package com.bridgelabz.fundooadminmicroservice.model;

import java.io.File;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.bridgelabz.fundooadminmicroservice.dto.FundooUserDTO;

import lombok.Data;
import lombok.extern.java.Log;

/**
 * Purpose:Creating model for user
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "user_details")
public class FundooUserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String name;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean isActive;
	private Boolean isDelete;
	private String dateOfBirth;
	private String phoneNumber;
	@Lob
	private byte[] profilePic;

	public FundooUserModel(FundooUserDTO fundooUserDTO) {
		this.name = fundooUserDTO.getName();
		this.email = fundooUserDTO.getEmail();
		this.password = fundooUserDTO.getPassword();
		this.dateOfBirth = fundooUserDTO.getDateOfBirth();
		this.phoneNumber = fundooUserDTO.getPhoneNumber();
	}

	public FundooUserModel() {

	}
}
