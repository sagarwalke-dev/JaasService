package com.jaas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;
import com.jaas.entity.UserDetails;

public interface IUserController {

	/**
	 * Validate user credential with database.
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public LoginResponse validateUser(@RequestBody User user);

	/**
	 * Register new user
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping(value="/register")
	public String registerUser(@RequestBody UserDetails user);

}
