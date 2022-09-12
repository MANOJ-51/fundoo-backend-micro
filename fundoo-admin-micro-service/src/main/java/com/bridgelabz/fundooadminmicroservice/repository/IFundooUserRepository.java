package com.bridgelabz.fundooadminmicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundooadminmicroservice.model.FundooUserModel;

/**
 * Purpose:Creating repository for note
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface IFundooUserRepository extends JpaRepository<FundooUserModel, Long> {

	Optional<FundooUserModel> findByEmail(String emailId);

}
