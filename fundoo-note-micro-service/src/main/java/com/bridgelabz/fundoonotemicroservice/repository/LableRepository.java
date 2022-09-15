package com.bridgelabz.fundoonotemicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoonotemicroservice.model.LableModel;

/**
 * Purpose:Creating repository for lable
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface LableRepository extends JpaRepository<LableModel, Long> {

}
