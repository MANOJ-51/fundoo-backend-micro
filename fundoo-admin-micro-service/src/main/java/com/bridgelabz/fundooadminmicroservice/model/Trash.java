package com.bridgelabz.fundooadminmicroservice.model;

import java.io.File;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.fundooadminmicroservice.dto.FundooUserDTO;

import lombok.Data;

/**
 * Purpose:Creating model for trash
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "trash")
public class Trash {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trashId;
	private Long Id;
	private String name;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean isActive;
	private String dateOfBirth;
	private String phoneNumber;
	private File profilePic;

}