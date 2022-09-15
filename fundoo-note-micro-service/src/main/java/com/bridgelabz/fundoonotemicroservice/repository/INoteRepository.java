package com.bridgelabz.fundoonotemicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoonotemicroservice.model.NoteModel;

/**
 * Purpose:Creating repository for note
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface INoteRepository extends JpaRepository<NoteModel, Long> {

	@Query(value = "select * from note_details where isTrash = true ", nativeQuery = true)
	List<NoteModel> findByIsPin();

	@Query(value = "select * from note_details where isArchieve = true ", nativeQuery = true)
	List<NoteModel> findByIsArchive();

	@Query(value = "select * from note_details where isTrash = true ", nativeQuery = true)
	List<NoteModel> findByTrash();

}
