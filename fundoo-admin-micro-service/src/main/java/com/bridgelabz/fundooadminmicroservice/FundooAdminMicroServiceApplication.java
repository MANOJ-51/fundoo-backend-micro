package com.bridgelabz.fundooadminmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FundooAdminMicroServiceApplication {

	@Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();

    }
	public static void main(String[] args) {
		SpringApplication.run(FundooAdminMicroServiceApplication.class, args);
	}

}
