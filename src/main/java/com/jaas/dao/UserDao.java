package com.jaas.dao;

import com.jaas.entity.User;

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
	public User getUser(User user);
}
