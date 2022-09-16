package com.bridgelabz.fundoonotemicroservice.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.bridgelabz.fundoonotemicroservice.dto.LableDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Purpose:Creating model for lable
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "lable_details")
public class LableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lableId;
	private String lableName;
	private LocalDateTime createdDate;
	private LocalDateTime updatedTime;
	private Long userId;
	@JsonIgnore
	@ManyToMany(mappedBy = "lableList")
	private List<NoteModel> notes;

	public LableModel(LableDTO lableDto) {
		this.lableName = lableDto.getLableName();
	}

	public LableModel() {

	}
}
