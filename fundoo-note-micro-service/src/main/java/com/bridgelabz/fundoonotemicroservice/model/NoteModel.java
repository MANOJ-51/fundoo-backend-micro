package com.bridgelabz.fundoonotemicroservice.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.bridgelabz.fundoonotemicroservice.dto.NoteDTO;

import lombok.Data;

/**
 * Purpose:Creating model for note
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "note_details")
public class NoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long noteId;
	private String title;
	private String description;
	private String email;
	private LocalDateTime registerDate;
	private LocalDateTime updateDate;
	private boolean isTrash;
	private Long userId;
	private boolean isArchieve;
	private boolean isPin;
	private String color;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reminderDate;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "notes_lables", joinColumns = @JoinColumn(referencedColumnName = "noteId"), inverseJoinColumns = @JoinColumn(referencedColumnName = "lableId"))
	private List<LableModel> lableList;
	@ElementCollection(targetClass = String.class)
	private List<String> collaborator;

	public NoteModel(NoteDTO noteDTO) {
		this.title = noteDTO.getTitle();
		this.description = noteDTO.getDescription();
		this.color = noteDTO.getColor();
		this.email = noteDTO.getEmail();
	}

	public NoteModel() {

	}
}
