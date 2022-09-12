package com.bridgelabz.fundoonotemicroservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * Purpose:Creating method to add note
	 * 
	 * @author Manoj
	 * @Param fundoo note dto
	 */
	@PostMapping("/createUser")
	public ResponseEntity<ResponseClass> createUser(@RequestHeader String token, @RequestBody @Valid NoteDTO noteDto) {
		ResponseClass responseClass = iNoteService.createUser(token, noteDto);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to update note
	 * 
	 * @author Manoj
	 * @Param token,title,email,description,color,userId,lableId,noteId
	 */
	@PutMapping("update/{noteId}")
	public ResponseEntity<ResponseClass> updateUser(@RequestHeader String token,@PathVariable Long noteId, @RequestParam String title,
			@RequestParam String email, @RequestParam String description, @RequestParam String color,
			@RequestParam Long userId, @RequestParam Long lableId) {

		ResponseClass responseClass = iNoteService.updateUser(token,noteId,title,email,description,color,userId,lableId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to get List of note
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/userList")
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
	@PostMapping("/noteTrash/{userId}")
	public ResponseEntity<ResponseClass> noteTrash(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.noteTrash(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to retrieve note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PostMapping("/retrieveNote/{userId}")
	public ResponseEntity<ResponseClass> retrieveNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.retrieveNote(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to delete note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@DeleteMapping("/deleteNote/{userId}")
	public ResponseEntity<ResponseClass> deleteNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.deleteNote(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PostMapping("/pinNote/{userId}")
	public ResponseEntity<ResponseClass> pinNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.pinNote(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to un pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PostMapping("/unPinNote/{userId}")
	public ResponseEntity<ResponseClass> unPinNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.unPinNote(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PostMapping("/archieveNote/{userId}")
	public ResponseEntity<ResponseClass> archieveNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.archieveNote(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to un archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@PostMapping("/unArchieveNote/{userId}")
	public ResponseEntity<ResponseClass> unArchieveNote(@RequestHeader String token, @PathVariable Long noteId) {
		ResponseClass responseClass = iNoteService.unArchieveNote(token,noteId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
