package com.bridgelabz.fundooadminmicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundooadminmicroservice.model.FundooUserModel;

public interface IFundooUserRepository extends JpaRepository<FundooUserModel, Long> {

	Optional<FundooUserModel> findByEmail(String emailId);

}
