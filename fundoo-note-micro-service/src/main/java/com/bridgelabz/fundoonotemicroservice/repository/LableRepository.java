package com.bridgelabz.fundoonotemicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoonotemicroservice.model.LableModel;

/**
 * Purpose:Creating repository for lable
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface LableRepository extends JpaRepository<LableModel, Long> {

	Optional<LableModel> findByUserId(Long usersId);

	@Query(value = "select * from lable_details where user_Id =:usersId ", nativeQuery = true)
	List<LableModel> findByUsersId(Long usersId);

}
