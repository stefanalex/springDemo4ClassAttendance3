package com.warpit.demo.security.web;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warpit.demo.security.AppUserDetailService;
import com.warpit.demo.security.JwtUtil;
import com.warpit.demo.security.domain.AuthenticationRequest;
import com.warpit.demo.security.domain.AuthenticationResponse;


@RestController
public class SecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserDetailService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
		
	@PostMapping("/authenticate")
	public AuthenticationResponse createAutenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
			);
		

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUserName());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return new AuthenticationResponse(jwt);
		
	}
	
	
	
	/**
	    * Exception handler if BadCredentialsException is thrown in this Controller
	    *
	    * @param ex exception
	    * @return Error message String.
	    */
	   @ResponseStatus(HttpStatus.UNAUTHORIZED)
	   @ExceptionHandler(BadCredentialsException.class)
	   public String return401(BadCredentialsException ex) {
	       return "Incorrect username or password";

	   }
}
