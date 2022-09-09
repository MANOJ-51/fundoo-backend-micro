package com.bridgelabz.fundooadminmicroservice.repository;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundooadminmicroservice.model.Trash;

public interface TrashRepository extends JpaRepository<Trash, Long> {

	Optional<Trash> findByPhoneNumber(String phoneNumber);

}