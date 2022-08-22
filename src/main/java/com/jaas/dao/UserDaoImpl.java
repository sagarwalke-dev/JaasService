package com.jaas.dao;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jaas.entity.User;
import com.jaas.utils.DBQueries;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	@Override
	public User getUser(User user) {
		try {
			log.info("calling getUser DAO");
			Object[] args= {user.getUsername()};
			return jdbcTemplate.queryForObject(DBQueries.GET_USER, args,User.class);
		} catch (Exception e) {
			log.error("Error while getUser DAO: {}",ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

}