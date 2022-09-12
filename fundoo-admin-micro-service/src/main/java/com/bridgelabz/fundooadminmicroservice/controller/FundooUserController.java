package com.bridgelabz.fundooadminmicroservice.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooadminmicroservice.dto.FundooUserDTO;
import com.bridgelabz.fundooadminmicroservice.model.FundooUserModel;
import com.bridgelabz.fundooadminmicroservice.service.IFundooUserService;
import com.bridgelabz.fundooadminmicroservice.util.FileUplodeResponse;
import com.bridgelabz.fundooadminmicroservice.util.FileUplodeUtill;
import com.bridgelabz.fundooadminmicroservice.util.ResponseClass;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Purpose:Creating APIS for user Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/user")
public class FundooUserController {

	@Autowired
	IFundooUserService iFundooUserService;

	/**
	 * Purpose:Creating method to add user
	 * 
	 * @author Manoj
	 * @Param fundoo user dto
	 */
	@PostMapping("/createUser")
	public ResponseEntity<ResponseClass> createUser(@Valid @RequestBody  FundooUserDTO fundooUserDTO) {
		ResponseClass responseClass = iFundooUserService.createUser(fundooUserDTO);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to Update user
	 * 
	 * @author Manoj
	 * @Param fundoo user Dto ,id ,token ,name ,email
	 */
	@PutMapping("/updateUser")
	public ResponseEntity<ResponseClass> updateUser(@RequestHeader String token, 
			@RequestParam String name, @RequestParam @NotBlank String email, @RequestParam @NotBlank String phoneNumber,
			@RequestParam @NotBlank String dateOfBirth, @RequestParam @NotBlank Boolean isActive) {
		ResponseClass responseClass = iFundooUserService.updateUser(token,  name, email, phoneNumber,
				dateOfBirth, isActive);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of user
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/userList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<FundooUserModel> responseClass = iFundooUserService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to Delete user
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ResponseClass> deleteUser(@RequestHeader String token, @PathVariable Long userId) {
		ResponseClass responseClass = iFundooUserService.deleteUser(token, userId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to retrieve user
	 * 
	 * @author Manoj
	 * @Param phone number
	 */
	@PostMapping("/retrieveUser")
	public ResponseEntity<ResponseClass> retrieveUser(@RequestHeader String token,@RequestParam Long userId) {
		ResponseClass responseClass = iFundooUserService.retrieveUser(token,userId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to permanent Delete user
	 * 
	 * @author Manoj
	 * @Param phone number
	 */
	@DeleteMapping("/deleteUserPermanent")
	public ResponseEntity<ResponseClass> deleteUserPermanent(@RequestHeader String token,@RequestParam Long userId) {
		ResponseClass responseClass = iFundooUserService.deleteUserPermanent(token,userId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method Login to user using unique id called token
	 * 
	 * @author Manoj
	 * @Param email,password
	 */
	@PostMapping("/loginToken")
	public ResponseEntity<ResponseClass> login(@RequestParam String emailId, @RequestParam String password) {
		ResponseClass responseClass = iFundooUserService.loginToken(emailId, password);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method Reset password of user
	 * 
	 * @author Manoj
	 * @Param email
	 */
	@PostMapping("/resetAdminPassword")
	public ResponseEntity<ResponseClass> resetPassword(@RequestParam String email) {
		ResponseClass responseClass = iFundooUserService.resetPassword(email);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method change password of user
	 * 
	 * @author Manoj
	 * @Param token,newPassword
	 */
	@PutMapping("/changeAdminPassword")
	public ResponseEntity<ResponseClass> changePassword(@RequestHeader String token, @RequestParam String newPassword) {
		ResponseClass responseClass = iFundooUserService.changePassword(token, newPassword);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to uplode file
	 * 
	 * @author Manoj
	 * @Param multipartfile
	 */
	@PostMapping("/uplodefile")
	public ResponseEntity<FileUplodeResponse> uplodeFile(@RequestParam("file") MultipartFile multipartFile)
			throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Long size = multipartFile.getSize();

		FileUplodeUtill.saveFile(fileName, multipartFile);

		FileUplodeResponse fileUplodeResponse = new FileUplodeResponse();
		fileUplodeResponse.setFileName(fileName);
		fileUplodeResponse.setDownlodeUrl("/downlodeFile");
		fileUplodeResponse.setSize(size);

		return new ResponseEntity<>(fileUplodeResponse, HttpStatus.OK);
	}
	
	  /**
     * Purpose:Creating method to validate user using token
     * @author Manoj
     * @Param  token
     */
    @GetMapping("/validate/{token}")
    public boolean validate(@PathVariable String token){
        return iFundooUserService.validateToken(token);
    }
}
