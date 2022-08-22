package com.jaas.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;

import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sagarwalke.dev
 *
 */
@Slf4j
@Controller
public class UserController implements IUserController {

	@Override
	public LoginResponse validateUser(User user) {
		try {
			log.info("calling validateUser");
		} catch (Exception e) {
			log.error("Error in validateUser: {}", ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

}
