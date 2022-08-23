package com.jaas.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;
import com.jaas.entity.UserDetails;
import com.jaas.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sagarwalke.dev
 *
 */
@Slf4j
@RestController("/user")

public class UserController implements IUserController {

	@Autowired
	UserService userService;

	@Override
	public LoginResponse validateUser(User user) {
		try {
			log.info("calling validateUser");
			return userService.validateUser(user);
		} catch (Exception e) {
			log.error("Error in validateUser: {}", ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

	@Override
	public String registerUser(UserDetails user) {
		try {
			log.info("calling registerUser");
			log.info("userdetails: {}",user);
			return userService.addUser(user);
		} catch (Exception e) {
			log.error("Error in registerUser: {}", ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

}
