package com.bridgelabz.fundooadminmicroservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

/**
 * Purpose:Creating token class
 * 
 * @author Manoj
 * @Param create,decode Version 1.0
 */
@Component
public class TokenUtill {
	private final String TOKEN_SECRET = "LoginToken";

	/**
	 * Purpose:Creating method to create a token
	 * 
	 * @author Manoj
	 * @Param id
	 */
	public String createToken(Long id) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			return JWT.create().withClaim("user_id", id).sign(algorithm);
		} catch (IllegalArgumentException | JWTCreationException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * Purpose:Creating method to decode a token
	 * 
	 * @author Manoj
	 * @Param token
	 */
	public Long decodeToken(String token) {
		Long userId;
		Verification verification = null;
		try {
			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		}
		JWTVerifier jwtVerifier = verification.build();
		DecodedJWT decodedJWT = jwtVerifier.verify(token);
		Claim claim = decodedJWT.getClaim("user_id");
		userId = claim.asLong();
		return userId;
	}
}
