package com.bridgelabz.fundooadminmicroservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundooadminmicroservice.dto.FundooUserDTO;
import com.bridgelabz.fundooadminmicroservice.exceptions.CustomExceptions;
import com.bridgelabz.fundooadminmicroservice.model.FundooUserModel;
import com.bridgelabz.fundooadminmicroservice.model.Trash;
import com.bridgelabz.fundooadminmicroservice.repository.IFundooUserRepository;
import com.bridgelabz.fundooadminmicroservice.repository.TrashRepository;
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
	TrashRepository trashRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	MailService mailService;

	/**
	 * Purpose:Creating method to add user
	 * 
	 * @author Manoj
	 * @Param fundoo dto
	 */
	@Override
	public ResponseClass createUser(@Valid FundooUserDTO fundooUserDTO) {
		FundooUserModel fundooUserModel = new FundooUserModel(fundooUserDTO);
		fundooUserModel.setCreatedAt(LocalDateTime.now());
		iFundooUserRepository.save(fundooUserModel);
		String body = "User Registration Is Successful with id :-" + fundooUserModel.getId() + "\n" + fundooUserModel;
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
	public ResponseClass updateUser(String token, Long userId, String name, String email, String phoneNumber,
			String dateOfBirth, Boolean isActive) {
		Long usersId = tokenUtill.decodeToken(token);
		Optional<FundooUserModel> isUserIdPresent = iFundooUserRepository.findById(usersId);
		if (isUserIdPresent.isPresent()) {
			Optional<FundooUserModel> isUserPresent = iFundooUserRepository.findById(userId);
			if (isUserPresent.isPresent()) {
				isUserPresent.get().setName(name);
				isUserPresent.get().setEmail(email);
				isUserPresent.get().setDateOfBirth(dateOfBirth);
				isUserPresent.get().setIsActive(isActive);
				isUserPresent.get().setPhoneNumber(phoneNumber);
				isUserPresent.get().setUpdatedAt(LocalDateTime.now());
				iFundooUserRepository.save(isUserPresent.get());
				String body = "User Update Is Successful with id :-" + isUserPresent.get().getId() + "\n"
						+ isUserPresent.get();
				String subject = "User Update Success";
				mailService.send(isUserPresent.get().getEmail(), body, subject);
				return new ResponseClass(200, "success", isUserPresent.get());
			}
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
				Trash trash = new Trash();
				trash.setId(isUserPresent.get().getId());
				trash.setName(isUserPresent.get().getName());
				trash.setEmail(isUserPresent.get().getEmail());
				trash.setDateOfBirth(isUserPresent.get().getDateOfBirth());
				trash.setPassword(isUserPresent.get().getPassword());
				trash.setIsActive(isUserPresent.get().getIsActive());
				trash.setCreatedAt(isUserPresent.get().getCreatedAt());
				trash.setUpdatedAt(isUserPresent.get().getUpdatedAt());
				trash.setPhoneNumber(isUserPresent.get().getPhoneNumber());
				trashRepository.save(trash);
				iFundooUserRepository.delete(isUserPresent.get());
				String body = "User Deleted Is Successful with id :-" + isUserPresent.get().getId() + "\n"
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
	public ResponseClass retrieveUser(String phoneNumber) {
		Optional<Trash> isEmailPresent = trashRepository.findByPhoneNumber(phoneNumber);
		if (isEmailPresent.isPresent()) {
			FundooUserModel fundooUserModel = new FundooUserModel();
			fundooUserModel.setId(isEmailPresent.get().getId());
			fundooUserModel.setName(isEmailPresent.get().getName());
			fundooUserModel.setEmail(isEmailPresent.get().getEmail());
			fundooUserModel.setDateOfBirth(isEmailPresent.get().getDateOfBirth());
			fundooUserModel.setIsActive(isEmailPresent.get().getIsActive());
			fundooUserModel.setPassword(isEmailPresent.get().getPassword());
			fundooUserModel.setCreatedAt(isEmailPresent.get().getCreatedAt());
			fundooUserModel.setUpdatedAt(isEmailPresent.get().getUpdatedAt());
			fundooUserModel.setPhoneNumber(isEmailPresent.get().getPhoneNumber());
			iFundooUserRepository.save(fundooUserModel);
			trashRepository.delete(isEmailPresent.get());
			String body = "User Deleted Is Successful with id :-" + isEmailPresent.get().getId() + "\n"
					+ isEmailPresent.get();
			String subject = "User Deleted Success";
			mailService.send(isEmailPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isEmailPresent.get());
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
	public ResponseClass deleteUserPermanent(String phoneNumber) {
		Optional<Trash> isEmailPresent = trashRepository.findByPhoneNumber(phoneNumber);
		if (isEmailPresent.isPresent()) {
			trashRepository.delete(isEmailPresent.get());
			return new ResponseClass(200, "success", isEmailPresent.get());
		}
		throw new CustomExceptions(400, "User Not Found");

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
			if (isEmailPresent.get().getPassword().equals(password)) {
				String token = tokenUtill.createToken(isEmailPresent.get().getId());
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
			String token = tokenUtill.createToken(isEmailPresent.get().getId());
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
			isUserIdPresent.get().setPassword(newPassword);
			iFundooUserRepository.save(isUserIdPresent.get());
			String body = "Admin Change Password Is Successful with id :-" + isUserIdPresent.get().getId() + "\n"
					+ isUserIdPresent.get();
			String subject = "Admin Change Password Success";
			mailService.send(isUserIdPresent.get().getEmail(), body, subject);
			return new ResponseClass(200, "success", isUserIdPresent.get());
		}
		throw new CustomExceptions(400, "Invalid Token");
	}

}
