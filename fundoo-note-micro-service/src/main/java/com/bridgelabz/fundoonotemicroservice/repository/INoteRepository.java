package com.bridgelabz.fundoonotemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoonotemicroservice.model.NoteModel;

/**
 * Purpose:Creating repository for note
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface INoteRepository extends JpaRepository<NoteModel, Long> {

}
