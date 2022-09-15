package com.bridgelabz.fundoonotemicroservice.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotemicroservice.dto.NoteDTO;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;

/**
 * Purpose:Creating interface for note
 * 
 * @author Manoj Version 1.0
 */
public interface INoteService {

	ResponseClass createNote(String token, @Valid NoteDTO noteDto);

	List<NoteModel> getList(String token);

	ResponseClass noteTrash(String token, Long noteId);

	ResponseClass retrieveNote(String token, Long noteId);

	ResponseClass deleteNote(String token, Long noteId);

	ResponseClass pinNote(String token, Long noteId);

	ResponseClass unPinNote(String token, Long noteId);

	ResponseClass archieveNote(String token, Long noteId);

	ResponseClass unArchieveNote(String token, Long noteId);

	List<NoteModel> pinList(String token);

	ResponseClass updateUser(String token, Long noteId, String description, String color, String title);

	ResponseClass addCollab(String email, Long noteId, List<String> collaborators);

	List<NoteModel> archiveList(String token);

	List<NoteModel> trashList(String token);

	ResponseClass addLables(String token, Long noteId, List<Long> lableId);

	ResponseClass addRemainder(String token, Long noteId, Date remainder);

}
