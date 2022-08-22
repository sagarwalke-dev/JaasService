package com.jaas.controller;

import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;

public interface IUserController {

	/**
	 * Validate user credential with database.
	 * 
	 * @param user
	 * @return
	 */
	public LoginResponse validateUser(User user);
}
