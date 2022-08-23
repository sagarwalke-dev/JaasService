package com.jaas.dao;

import com.jaas.entity.User;
import com.jaas.entity.UserDetails;

/**
 * @author sagarwalke.dev
 *
 */
public interface UserDao {
	/**
	 * Get user info from database.
	 * 
	 * @param user
	 * @return
	 */
	public User getUserByName(String username);
	
	public Boolean addUser(UserDetails user);
}
