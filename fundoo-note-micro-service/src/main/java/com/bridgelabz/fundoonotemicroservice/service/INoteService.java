package com.bridgelabz.fundoonotemicroservice.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotemicroservice.dto.NoteDTO;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;

public interface INoteService {

	ResponseClass createUser(String token,@Valid NoteDTO noteDto);

	ResponseClass updateUser(String token, Long noteId,String title, String email, String description, String color, Long userId,
			Long lableId);

	List<NoteModel> getList(String token);

	ResponseClass noteTrash(String token, Long noteId);

	ResponseClass retrieveNote(String token, Long noteId);

	ResponseClass deleteNote(String token, Long noteId);

	ResponseClass pinNote(String token, Long noteId);

	ResponseClass unPinNote(String token, Long noteId);

	ResponseClass archieveNote(String token, Long noteId);

	ResponseClass unArchieveNote(String token, Long noteId);

}
