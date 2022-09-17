package com.bridgelabz.fundoonotemicroservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.fundoonotemicroservice.dto.LableDTO;
import com.bridgelabz.fundoonotemicroservice.exceptions.CustomExceptions;
import com.bridgelabz.fundoonotemicroservice.model.LableModel;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.repository.INoteRepository;
import com.bridgelabz.fundoonotemicroservice.repository.LableRepository;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;
import com.bridgelabz.fundoonotemicroservice.utill.TokenUtill;

/**
 * Purpose:Creating Service for Lable
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class LableService implements ILableService {

	@Autowired
	LableRepository lableRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	INoteRepository iNoteRepository;

	/**
	 * Purpose:Creating method to add lable
	 * 
	 * @author Manoj
	 * @Param lable dto
	 */
	@Override
	public ResponseClass createLable(String token, @Valid LableDTO lableDto) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			LableModel lableModel = new LableModel(lableDto);
			lableModel.setCreatedDate(LocalDateTime.now());
			lableModel.setUserId(usersId);
			lableRepository.save(lableModel);
			return new ResponseClass(200, "success", lableModel);
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to update lable
	 * 
	 * @author Manoj
	 * @Param token,labelId
	 */
	@Override
	public ResponseClass updateLable(String token, Long lableId, @Valid LableDTO lableDto) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<LableModel> isUseridPresent = lableRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<LableModel> isLablePresent = lableRepository.findById(lableId);
				if (isLablePresent.isPresent()) {
					isLablePresent.get().setLableName(lableDto.getLableName());
					isLablePresent.get().setUpdatedTime(LocalDateTime.now());
					lableRepository.save(isLablePresent.get());
					return new ResponseClass(200, "success", isLablePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of lable
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<LableModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
				List<LableModel> getList = lableRepository.findByUsersId(usersId);
				if (getList.size() > 0) {
					return getList;
				}
			}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to delete lable
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass deleteNote(String token, Long lableId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<LableModel> isUseridPresent = lableRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<LableModel> isLablePresent = lableRepository.findById(lableId);
				if (isLablePresent.isPresent()) {
					lableRepository.delete(isLablePresent.get());
					return new ResponseClass(200, "success", isLablePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "note is not in trash");
	}
}
