package com.bridgelabz.fundoonotemicroservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private Long userId;
	private LocalDateTime registerDate;
	private LocalDateTime updateDate;
	private boolean isTrash;
	private boolean isArchieve;
	private boolean isPin;
	private Long lableId;
	private String email;
	private String color;
	private LocalDateTime remindertime;
	@ElementCollection(targetClass = String.class)
	private List<String> collaborator;

	public NoteModel(NoteDTO noteDTO) {
		this.title = noteDTO.getTitle();
		this.description = noteDTO.getDescription();
		this.userId = noteDTO.getUserId();
		this.lableId = noteDTO.getLableId();
		this.email = noteDTO.getEmail();
		this.color = noteDTO.getColor();
		this.collaborator = noteDTO.getCollaborator();
	}

	public NoteModel() {

	}
}
