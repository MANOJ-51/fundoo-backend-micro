package com.bridgelabz.fundoonotemicroservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.bridgelabz.fundoonotemicroservice.dto.NoteDTO;
import com.bridgelabz.fundoonotemicroservice.exceptions.CustomExceptions;
import com.bridgelabz.fundoonotemicroservice.model.LableModel;
import com.bridgelabz.fundoonotemicroservice.model.NoteModel;
import com.bridgelabz.fundoonotemicroservice.repository.INoteRepository;
import com.bridgelabz.fundoonotemicroservice.repository.LableRepository;
import com.bridgelabz.fundoonotemicroservice.utill.ResponseClass;
import com.bridgelabz.fundoonotemicroservice.utill.TokenUtill;
import com.bridgelabz.fundoonotemicroservice.utill.UserResponse;

/**
 * Purpose:Creating Service for note
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class NoteService implements INoteService {

	@Autowired
	INoteRepository iNoteRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	LableRepository lableRepository;

	/**
	 * Purpose:Creating method to add note
	 * 
	 * @author Manoj
	 * @Param fundoo note dto
	 */
	@Override
	public ResponseClass createNote(String token, @Valid NoteDTO noteDto) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {

			Long usersId = tokenUtill.decodeToken(token);
			NoteModel noteModel = new NoteModel(noteDto);
			noteModel.setUserId(usersId);
			noteModel.setArchieve(false);
			noteModel.setPin(false);
			noteModel.setTrash(false);
			noteModel.setRegisterDate(LocalDateTime.now());
			iNoteRepository.save(noteModel);
			return new ResponseClass(200, "success", noteModel);
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to update note
	 * 
	 * @author Manoj
	 * @Param token,title,email,description,color,userId,lableId,noteId
	 */
	@Override
	public ResponseClass updateUser(String token, Long noteId, String description, String title) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent()) {
					isNotePresent.get().setTitle(title);
					isNotePresent.get().setDescription(description);
					isNotePresent.get().setUpdateDate(LocalDateTime.now());
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of note
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<NoteModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<NoteModel> getList = iNoteRepository.findByUsersId(usersId);
			if (getList.size() > 0) {
				return getList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to move note to trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass noteTrash(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent()) {
					isNotePresent.get().setTrash(true);
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to retrieve note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass retrieveNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent()) {
					isNotePresent.get().setTrash(false);
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to delete note from trash
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass deleteNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent() && isNotePresent.get().isTrash() == true) {
					iNoteRepository.delete(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "note is not in trash");
	}

	/**
	 * Purpose:Creating method to pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass pinNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent() && isNotePresent.get().isTrash() == false) {
					isNotePresent.get().setPin(true);
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to un pin note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass unPinNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent() && isNotePresent.get().isTrash() == false) {
					isNotePresent.get().setPin(false);
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass archieveNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent() && isNotePresent.get().isTrash() == false) {
					isNotePresent.get().setArchieve(true);
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to un archieve note
	 * 
	 * @author Manoj
	 * @Param token,id
	 */
	@Override
	public ResponseClass unArchieveNote(String token, Long noteId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);
				if (isNotePresent.isPresent() && isNotePresent.get().isTrash() == false) {
					isNotePresent.get().setArchieve(false);
					iNoteRepository.save(isNotePresent.get());
					return new ResponseClass(200, "success", isNotePresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get pin list
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<NoteModel> pinList(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<NoteModel> pinList = iNoteRepository.findByIsPin(usersId);
			if (pinList.size() > 0) {
				return pinList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to add collaborators
	 * 
	 * @author Manoj
	 * @Param email,notesId,collaborators
	 */
	@Override
	public ResponseClass addCollab(String token, String email, Long noteId, String collaborator, Long collabUserId) {

		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);

		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);

			if (isUseridPresent.isPresent()) {
				UserResponse isEmailPresent = restTemplate.getForObject("http://localhost:5666/user/validateEmail/" + email,
						UserResponse.class);

				if (isEmailPresent != null) {
					Optional<NoteModel> isNotePresent = iNoteRepository.findById(noteId);

					if (isNotePresent.isPresent()) {
						List<String> collabList = new ArrayList<>();
						UserResponse isEmailIdPresent = restTemplate
								.getForObject("http://localhost:5666/user/validateEmail/" + collaborator, UserResponse.class);

						if (isEmailIdPresent != null) {
							collabList.add(collaborator);
						} else {
							throw new CustomExceptions(400, "email is not present");
						}

						isNotePresent.get().setCollaborator(collabList);
						iNoteRepository.save(isNotePresent.get());
						List<String> noteList = new ArrayList<>();
						noteList.add(isNotePresent.get().getEmail());

						NoteModel noteModel = new NoteModel();
						noteModel.setUserId(collabUserId);
						noteModel.setEmail(collaborator);
						noteModel.setTitle(isNotePresent.get().getTitle());
						noteModel.setDescription(isNotePresent.get().getDescription());
						noteModel.setCollaborator(noteList);
						iNoteRepository.save(noteModel);
						return new ResponseClass(200, "success", isNotePresent.get(), isEmailIdPresent);
					}
				} else {
					throw new CustomExceptions(400, "User Not Found");
				}
			}
		}
		throw new CustomExceptions(400, "email is not valid");
	}

	/**
	 * Purpose:Creating method to get archive list
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<NoteModel> archiveList(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<NoteModel> pinList = iNoteRepository.findByIsArchive(usersId);
			if (pinList.size() > 0) {
				return pinList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get trash list
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<NoteModel> trashList(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			List<NoteModel> pinList = iNoteRepository.findByTrash(usersId);
			if (pinList.size() > 0) {
				return pinList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get trash list
	 * 
	 * @author Manoj
	 * @Param token,noteId,lableId
	 */
	@Override
	public ResponseClass addLables(String token, Long noteId, List<Long> lableId) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNoteIdPresent = iNoteRepository.findById(noteId);
				if (isNoteIdPresent.isPresent()) {
					List<LableModel> isLableList = new ArrayList<>();
					lableId.stream().forEach(lablesId -> {
						Optional<LableModel> isIdPresent = lableRepository.findById(lablesId);
						if (isIdPresent.isPresent()) {
							isLableList.add(isIdPresent.get());
						}
					});

					isNoteIdPresent.get().setLableList(isLableList);
					iNoteRepository.save(isNoteIdPresent.get());
					return new ResponseClass(200, "success", isNoteIdPresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to add remainder time
	 * 
	 * @author Manoj
	 * @Param token , noteId,remainder
	 */
	@Override
	public ResponseClass addRemainder(String token, Long noteId, Date remainder) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				Optional<NoteModel> isNoteIdPresent = iNoteRepository.findById(noteId);
				if (isNoteIdPresent.isPresent()) {
					isNoteIdPresent.get().setReminderDate(remainder);
					iNoteRepository.save(isNoteIdPresent.get());
					return new ResponseClass(200, "success", isNoteIdPresent.get());
				}
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	@Override
	public ResponseClass addColor(String token, String color) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:5666/user/validate/" + token,
				Boolean.class);
		if (isUserPresent) {
			Long usersId = tokenUtill.decodeToken(token);
			Optional<NoteModel> isUseridPresent = iNoteRepository.findByUserId(usersId);
			if (isUseridPresent.isPresent()) {
				isUseridPresent.get().setColor(color);
				iNoteRepository.save(isUseridPresent.get());
				return new ResponseClass(200, "success", isUseridPresent.get());
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}
}
