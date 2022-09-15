package com.bridgelabz.fundooadminmicroservice.service;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundooadminmicroservice.dto.FundooUserDTO;
import com.bridgelabz.fundooadminmicroservice.model.FundooUserModel;
import com.bridgelabz.fundooadminmicroservice.util.ResponseClass;

public interface IFundooUserService {

	ResponseClass createUser(@Valid FundooUserDTO fundooUserDTO);

	ResponseClass updateUser(String token, String name, String email, String phoneNumber, String dateOfBirth,
			Boolean isActive);

	List<FundooUserModel> getList(String token);

	ResponseClass deleteUser(String token, Long userId);

	ResponseClass retrieveUser(String token, Long userId);

	ResponseClass deleteUserPermanent(String token, Long userId);

	ResponseClass loginToken(String emailId, String password);

	ResponseClass resetPassword(String email);

	ResponseClass changePassword(String token, String newPassword);

	boolean validateToken(String token);

	ResponseClass addProfile(String token, MultipartFile multipartFile, Long userId) throws IOException;

	Boolean validateEmail(String email);

}
