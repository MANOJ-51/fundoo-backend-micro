package com.bridgelabz.fundooadminmicroservice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooadminmicroservice.dto.FundooUserDTO;
import com.bridgelabz.fundooadminmicroservice.exceptions.CustomExceptions;
import com.bridgelabz.fundooadminmicroservice.model.FundooUserModel;
import com.bridgelabz.fundooadminmicroservice.repository.IFundooUserRepository;
import com.bridgelabz.fundooadminmicroservice.util.ResponseClass;
import com.bridgelabz.fundooadminmicroservice.util.TokenUtill;

/**
 * Purpose:Creating Service for user
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class FundooUserService implements IFundooUserService {

	@Autowired
	IFundooUserRepository iFundooUserRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	MailService mailService;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Purpose:Creating method to add user
	 * 
	 * @author Manoj
	 * @Param fundoo dto
	 */
	@Override
	public ResponseClass createUser(@Valid FundooUserDTO fundooUserDTO) {
		FundooUserModel fundooUserModel = new FundooUserModel(fundooUserDTO);
		fundooUserModel.setPassword(passwordEncoder.encode(fundooUserDTO.getPassword()));
		fundooUserModel.setCreatedAt(LocalDateTime.now());
		fundooUserModel.setIsActive(true);
		fundooUserModel.setIsDelete(false);
		iFundooUserRepository.save(fundooUserModel);
		String body = "User Registration Is Successful with id :-" + fundooUserModel.getUserId() + "\n"
				+ fundooUserModel;
		String subject = "User Registration Success";
		mailService.send(fundooUserModel.getEmail(), body, subject);
		return new ResponseClass(200, "success", fundooUserModel);
	}

	/**
	 * Purpose:Creating method to Update user
	 * 
	 * @author Manoj
	 * @Param fundoo user Dto ,id ,token,name,email,phone number
	 */
	@Override
	public ResponseClass updateUser(String token, String name, String email, String phoneNumber, String dateOfBirth,
			Boolean isActive) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(usersId);
		if (isUserPresent.isPresent()) {
			isUserPresent.get().setName(name);
			isUserPresent.get().setEmail(email);
			isUserPresent.get().setDateOfBirth(dateOfBirth);
			isUserPresent.get().setIsActive(isActive);
			isUserPresent.get().setPhoneNumber(phoneNumber);
			isUserPresent.get().setUpdatedAt(LocalDateTime.now());
			iFundooUserRepository.save(isUserPresent.get());
			String body = "User Update Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
					+ isUserPresent.get();
			String subject = "User Update Success";
			mailService.send(isUserPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isUserPresent.get());
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to getList
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<FundooUserModel> getList(String token) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserIdPresent = iFundooUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			List<FundooUserModel> getData = iFundooUserRepository.findAll();
			if (getData.size() > 0) {
				return getData;
			}
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to delete user
	 * 
	 * @author Manoj
	 * @Param id ,token
	 */
	@Override
	public ResponseClass deleteUser(String token, Long userId) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserIdPresent = iFundooUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(userId);
			if (isUserPresent.isPresent()) {
				isUserPresent.get().setIsActive(false);
				isUserPresent.get().setIsDelete(true);
				iFundooUserRepository.save(isUserPresent.get());
				String body = "User Deleted Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
						+ isUserPresent.get();
				String subject = "User Deleted Success";
				mailService.send(isUserPresent.get().getEmail(), body, subject);
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to delete user
	 * 
	 * @author Manoj
	 * @Param phone number
	 */
	@Override
	public ResponseClass retrieveUser(String token, Long userId) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserIdPresent = iFundooUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(userId);
			if (isUserPresent.isPresent() && isUserPresent.get().getIsDelete() == true) {
				isUserPresent.get().setIsDelete(false);
				isUserPresent.get().setIsActive(true);
				iFundooUserRepository.save(isUserPresent.get());
				String body = "User retrieved Is Successful with id :-" + isUserPresent.get().getUserId() + "\n"
						+ isUserPresent.get();
				String subject = "User retrieved Success";
				mailService.send(isUserPresent.get().getEmail(), body, subject);
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "User Not Found");
	}

	/**
	 * Purpose:Creating method to delete user permanently
	 * 
	 * @author Manoj
	 * @Param phone number
	 */
	@Override
	public ResponseClass deleteUserPermanent(String token, Long userId) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserIdPresent = iFundooUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(userId);
			if (isUserPresent.isPresent() && isUserPresent.get().getIsDelete() == true) {
				iFundooUserRepository.delete(isUserPresent.get());
				return new ResponseClass(200, "success", isUserPresent.get());
			}
		}
		throw new CustomExceptions(400, "User Not Found in trash");

	}

	/**
	 * Purpose:Creating method to login token
	 * 
	 * @author Manoj
	 * @Param emailid ,password
	 */
	@Override
	public ResponseClass loginToken(String emailId, String password) {
		Optional<FundooUserModel> isEmailPresent = iFundooUserRepository.findByEmail(emailId);
		if (isEmailPresent.isPresent()) {
			// if (isEmailPresent.get().getPassword().equals(password)) {
			if (passwordEncoder.matches(password, isEmailPresent.get().getPassword())) {
				String token = tokenUtill.createToken(isEmailPresent.get().getUserId());
				return new ResponseClass(200, "Login Success", token);
			} else {
				throw new CustomExceptions(400, "Password is Wrong");
			}
		}
		throw new CustomExceptions(400, "No Details Matched");
	}

	/**
	 * Purpose:Creating method to reset user password
	 * 
	 * @author Manoj
	 * @Param email
	 */
	@Override
	public ResponseClass resetPassword(String email) {
		Optional<FundooUserModel> isEmailPresent = iFundooUserRepository.findByEmail(email);
		if (isEmailPresent.isPresent()) {
			String token = tokenUtill.createToken(isEmailPresent.get().getUserId());
			String url = System.getenv("url") + "\n" + token;
			String subject = "Admin reset Success";
			mailService.send(isEmailPresent.get().getEmail(), url, subject);
		}
		throw new CustomExceptions(400, "Invalid Email");
	}

	/**
	 * Purpose:Creating method to change password
	 * 
	 * @author Manoj
	 * @Param token,new password
	 */
	@Override
	public ResponseClass changePassword(String token, String newPassword) {
		Long userId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserIdPresent = iFundooUserRepository.findById(userId);
		if (isUserIdPresent.isPresent()) {
			isUserIdPresent.get().setPassword(passwordEncoder.encode(newPassword));
			iFundooUserRepository.save(isUserIdPresent.get());
			String body = "Admin Change Password Is Successful with id :-" + isUserIdPresent.get().getUserId() + "\n"
					+ isUserIdPresent.get();
			String subject = "Admin Change Password Success";
			mailService.send(isUserIdPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isUserIdPresent.get());
		}
		throw new CustomExceptions(400, "Invalid Token");
	}

	/**
	 * Purpose:Creating method to validate user using token
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public boolean validateToken(String token) {
		Long userId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(userId);
		if (isUserPresent.isPresent()) {
			return true;
		}
		return false;
	}

	/**
	 * Purpose:Creating method to addProfile
	 * 
	 * @author Manoj
	 * @throws IOException 
	 * @Param multipartfile
	 */
	@Override
	public ResponseClass addProfile(String token, MultipartFile multipartFile,Long userId) throws IOException {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(usersId);
		if (isUserPresent.isPresent()) {
			Optional <FundooUserModel> isIdPresent =iFundooUserRepository.findById(userId);
			if (isIdPresent.isPresent()) {
				isIdPresent.get().setProfilePic(multipartFile.getBytes());
				iFundooUserRepository.save(isIdPresent.get());
				return new ResponseClass(200, "success", isIdPresent.get());
			}
		}
		throw new CustomExceptions(400, "Invalid Token");
	}

	@Override
	public Boolean validateEmail(String email) {
		Optional <FundooUserModel> isEmailPresent = iFundooUserRepository.findByEmail(email);
		if (isEmailPresent.isPresent()) {
			return true;
		}
		return false;
	}

}
