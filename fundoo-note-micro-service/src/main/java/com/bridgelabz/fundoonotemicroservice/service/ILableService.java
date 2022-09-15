package com.bridgelabz.fundoonotemicroservice.service;

import java.util.List;
import javax.validation.Valid;
import com.bridgelabz.fundoonotemicroservice.dto.LableDTO;
import com.bridgelabz.fundoonotemicroservice.model.LableModel;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;

/**
 * Purpose:Creating interface for lable
 * 
 * @author Manoj Version 1.0
 */
public interface ILableService {

	ResponseClass createLable(String token, @Valid LableDTO lableDto);

	ResponseClass updateLable(String token, Long lableId, @Valid LableDTO lableDto);

	List<LableModel> getList(String token);

	ResponseClass deleteNote(String token, Long lableId);

}
