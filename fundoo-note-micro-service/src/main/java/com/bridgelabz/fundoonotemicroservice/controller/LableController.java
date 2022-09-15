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

import com.bridgelabz.fundoonotemicroservice.dto.LableDTO;
import com.bridgelabz.fundoonotemicroservice.model.LableModel;
import com.bridgelabz.fundoonotemicroservice.service.ILableService;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;

/**
 * Purpose:Creating APIS for lable Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/lable")
public class LableController {
	@Autowired
	ILableService iLableService;

	/**
	 * Purpose:Creating method to add lable
	 * 
	 * @author Manoj
	 * @Param lable dto
	 */
	@PostMapping("/createLable")
	public ResponseEntity<ResponseClass> createLable(@RequestHeader String token, @RequestBody @Valid LableDTO lableDto) {
		ResponseClass responseClass = iLableService.createLable(token, lableDto);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to update lable
	 * 
	 * @author Manoj
	 * @Param token,labelId
	 */
	@PutMapping("update/{lableId}")
	public ResponseEntity<ResponseClass> updatelable(@RequestHeader String token,@PathVariable Long lableId, @RequestBody @Valid LableDTO lableDto) {
		ResponseClass responseClass = iLableService.updateLable(token,lableId, lableDto);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to get List of lable
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/lableList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<LableModel> responseClass = iLableService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
	
	/**
	 * Purpose:Creating method to delete lable
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@DeleteMapping("/deleteNote/{lableId}")
	public ResponseEntity<ResponseClass> deleteNote(@RequestHeader String token, @PathVariable Long lableId) {
		ResponseClass responseClass = iLableService.deleteNote(token,lableId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
