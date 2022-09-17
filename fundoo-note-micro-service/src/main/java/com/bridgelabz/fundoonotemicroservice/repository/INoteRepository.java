package com.bridgelabz.fundoonotemicroservice.repository;

import java.util.List;
import java.util.Optional;

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

	@Query(value = "select * from note_details where is_Pin = true and user_Id =:usersId ", nativeQuery = true)
	List<NoteModel> findByIsPin(Long usersId);

	@Query(value = "select * from note_details where is_Archieve = true and user_Id =:usersId ", nativeQuery = true)
	List<NoteModel> findByIsArchive(Long usersId);

	@Query(value = "select * from note_details where is_Trash = true and user_Id =:usersId", nativeQuery = true)
	List<NoteModel> findByTrash(Long usersId);

	
	Optional<NoteModel> findByUserId(Long usersId);

	@Query(value = "select * from note_details where user_Id =:usersId ", nativeQuery = true)
	List<NoteModel> findByUsersId(Long usersId);
}
