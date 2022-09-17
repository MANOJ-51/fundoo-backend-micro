package com.bridgelabz.fundoonotemicroservice.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonotemicroservice.dto.NoteDTO;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.service.INoteService;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;

/**
 * Purpose:Creating APIS for note Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	INoteService iNoteService;

	/**
	 * Purpose:Creating method to add note with lable
	 * 
	 * @author Manoj
	 * @Param dto,token
	 */
	@PostMapping("/createNote")
	public ResponseEntity<ResponseClass> createNote(@RequestHeader String token, @RequestBody @Valid NoteDTO noteDto) {
		ResponseClass responseClass = iNoteService.createNote(token, noteDto);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to update note
	 * 
	 * @author Manoj
	 * @Param token,title,email,description,color,userId,lableId,noteId
	 */
	@PutMapping("update/{noteId}")
	public ResponseEntity<ResponseClass> updateUser(@RequestHeader String token, @PathVariable Long noteId,
			@RequestParam String title, @RequestParam String description) {

		ResponseClass responseClass = iNoteService.updateUser(token, noteId, title, description);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of note
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/noteList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<NoteModel> responseClass = iNoteService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to move note to trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PutMapping("/noteTrash/{noteId}")
	public ResponseEntity<ResponseClass> noteTrash(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.noteTrash(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to retrieve note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PutMapping("/retrieveNote/{noteId}")
	public ResponseEntity<ResponseClass> retrieveNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.retrieveNote(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to delete note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@DeleteMapping("/deleteNote/{noteId}")
	public ResponseEntity<ResponseClass> deleteNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.deleteNote(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PutMapping("/pinNote/{noteId}")
	public ResponseEntity<ResponseClass> pinNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.pinNote(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to un pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PutMapping("/unPinNote/{noteId}")
	public ResponseEntity<ResponseClass> unPinNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.unPinNote(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PutMapping("/archieveNote/{noteId}")
	public ResponseEntity<ResponseClass> archieveNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.archieveNote(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to un archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PutMapping("/unArchieveNote/{noteId}")
	public ResponseEntity<ResponseClass> unArchieveNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.unArchieveNote(token, noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of notes pinned
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/pinList")
	public ResponseEntity<List<?>> pinList(@RequestHeader String token) {
		List<NoteModel> responseClass = iNoteService.pinList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of notes archive
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/archiveList")
	public ResponseEntity<List<?>> archiveList(@RequestHeader String token) {
		List<NoteModel> responseClass = iNoteService.archiveList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of notes archive
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/trashList")
	public ResponseEntity<List<?>> trashList(@RequestHeader String token) {
		List<NoteModel> responseClass = iNoteService.trashList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to add collaborators
	 * 
	 * @author Manoj
	 * @Param email,notesId,collaborators
	 */
	@PostMapping("/addCollab")
	public ResponseEntity<ResponseClass> addCollab(@RequestHeader String token,@RequestParam String email, @RequestParam Long noteId,
			@RequestParam String collaborator,@RequestParam Long collabUserId) {
		ResponseClass responseClass = iNoteService.addCollab(token,email, noteId, collaborator,collabUserId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to add note with lable
	 * 
	 * @author Manoj
	 * @Param token,noteId,list of long
	 */
	@PostMapping("/addLables")
	public ResponseEntity<ResponseClass> addLables(@RequestHeader String token, @RequestParam Long noteId,
			@RequestParam List<Long> lableId) {
		ResponseClass responseClass = iNoteService.addLables(token, noteId, lableId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to add remainder time
	 * 
	 * @author Manoj
	 * @Param token , noteId,remainder
	 */
	@PostMapping("/addRemainder")
	public ResponseEntity<ResponseClass> addLables(@RequestHeader String token, @RequestParam Long noteId,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date remainder) {
		ResponseClass responseClass = iNoteService.addRemainder(token, noteId, remainder);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to add note with lable
	 * 
	 * @author Manoj
	 * @Param dto,token
	 */
	@PutMapping("/addColor")
	public ResponseEntity<ResponseClass> addColor(@RequestHeader String token, @RequestParam String color) {
		ResponseClass responseClass = iNoteService.addColor(token, color);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
