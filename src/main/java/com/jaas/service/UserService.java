package com.jaas.service;

import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;
import com.jaas.entity.UserDetails;

/**
 * @author sagarwalke.dev
 *
 */
public interface UserService {
	/**
	 * Perform the user validation
	 * 
	 * @param user
	 * @return
	 */
	public LoginResponse validateUser(User user);
	
	public String addUser(UserDetails user);
}
