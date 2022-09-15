package com.bridgelabz.fundoonotemicroservice.config;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.repository.INoteRepository;
import com.bridgelabz.fundoonotemicroservice.service.MailService;


/**
 * Purpose:Creating configuration for scheduling
 * 
 * @author Manoj
 * @Param  Version 1.0
 */
@Component
public class SchedulingConfiguration {

	@Autowired
	INoteRepository iNoteRepository;
	
	@Autowired
	MailService mailService;
	
	static Format formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Purpose:Creating method for email Scheduling
	 * 
	 * @author Manoj
	 * 
	 * @Param  Version 1.0
	 */
	@Scheduled(fixedDelay = 11000)
	public void emailScheudleJob () {
		List<NoteModel> noteList =iNoteRepository.findAll();
		for(NoteModel noteModel:noteList) {
			Date remainderDate =noteModel.getReminderDate();
			String remaiderDateFormate = formatter.format(remainderDate);
			String currentDate = formatter.format(new Date());
			
			if (remaiderDateFormate.equals(currentDate)) {
				try {
				String body = " You have a Remainder";
				String subject = "Remainder";
				mailService.send(noteModel.getEmail(), body, subject);
				System.out.println("remainder sent sucessfully");
				}catch(LazyInitializationException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
