package com.bridgelabz.fundoonotemicroservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.fundoonotemicroservice.dto.NoteDTO;
import com.bridgelabz.fundoonotemicroservice.exceptions.CustomExceptions;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.repository.INoteRepository;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;
import com.bridgelabz.fundoonotemicroservice.utill.TokenUtill;

/**
 * Purpose:Creating Service for note
 * @author Manoj
 * @Param business logic is present here
 * Version 1.0
 */
@Service
public class NoteService implements INoteService{

	@Autowired
	INoteRepository iNoteRepository;
	
	@Autowired
	TokenUtill tokenUtill;
	
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Purpose:Creating method to add note
	 * 
	 * @author Manoj
	 * @Param fundoo note dto
	 */
	@Override
	public ResponseClass createUser(String token,@Valid NoteDTO noteDto) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			NoteModel noteModel = new NoteModel(noteDto);
			noteModel.setArchieve(false);
			noteModel.setPin(false);
			noteModel.setTrash(false);
			noteModel.setRegisterDate(LocalDateTime.now());
			iNoteRepository.save(noteModel);
			return new ResponseClass(200,"success",noteModel);
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to update note
	 * 
	 * @author Manoj
	 * @Param token,title,email,description,color,userId,lableId,noteId
	 */
	@Override
	public ResponseClass updateUser(String token,Long noteId, String title, String email, String description, String color,
			Long userId, Long lableId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()) {
				isNotePresent.get().setTitle(title);
				isNotePresent.get().setEmail(email);
				isNotePresent.get().setDescription(description);
				isNotePresent.get().setColor(color);
				isNotePresent.get().setUserId(userId);
				isNotePresent.get().setLableId(lableId);
				isNotePresent.get().setUpdateDate(LocalDateTime.now());
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to get List of note
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<NoteModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
        if (isUserPresent) {
        	List<NoteModel> getList = iNoteRepository.findAll();
        	if (getList.size()>0) {
        		return getList;
        	}
        }
        throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to move note to trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass noteTrash(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()) {
				isNotePresent.get().setTrash(true);
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to retrieve note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass retrieveNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()) {
				isNotePresent.get().setTrash(false);
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to delete note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass deleteNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent() && isNotePresent.get().isTrash() == true) {
				iNoteRepository.delete(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"note is not in trash");
	}

	/**
	 * Purpose:Creating method to pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass pinNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()&& isNotePresent.get().isTrash() == false) {
				isNotePresent.get().setPin(true);
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to un pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass unPinNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()&& isNotePresent.get().isTrash() == false) {
				isNotePresent.get().setPin(false);
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass archieveNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()&& isNotePresent.get().isTrash() == false) {
				isNotePresent.get().setArchieve(true);
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}

	/**
	 * Purpose:Creating method to un archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass unArchieveNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://user-service/user/validate/"+token, Boolean.class);
		if (isUserPresent) {
			Optional<NoteModel>isNotePresent = iNoteRepository.findById(noteId);
			if(isNotePresent.isPresent()&& isNotePresent.get().isTrash() == false) {
				isNotePresent.get().setArchieve(false);
				iNoteRepository.save(isNotePresent.get());
				return new ResponseClass(200,"success",isNotePresent.get());
			}
		}
		throw new CustomExceptions(400,"token not valid");
	}
	
	
}
