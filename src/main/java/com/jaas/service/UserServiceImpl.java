package com.jaas.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaas.dao.UserDao;
import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;
import com.jaas.entity.UserDetails;
import com.jaas.utils.Base64Util;
import com.jaas.utils.CryptographyUtil;
import com.jaas.utils.JAASConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	CryptographyUtil cryptoUtil;

	@Autowired
	Base64Util base64Util;

	@Override
	public LoginResponse validateUser(User user) {
		try {
			LoginResponse loginResponse = new LoginResponse();
			log.info("calling validateUser service");
			User dbUser = userDao.getUserByName(user.getUsername());
			log.info("user details from DB : {}", dbUser);
			if (dbUser.getUsername() != null) {
				log.info("User found in DB : {}", dbUser.getUsername());
				// decrypt the password
				String decryped = cryptoUtil.decrypt(dbUser.getPassword());
				// decode the password
				String password = base64Util.decodeString(decryped);
				log.info("decoded data: {}", password);
				log.info("user data: {}", user.getPassword());
				if (password != null && user.getPassword() != null) {
					log.info("validating password");
					if (password.equals(user.getPassword())) {
						log.info("Valid user");
						loginResponse.setStatusCode(JAASConstant.SUCCESS_CODE);
						loginResponse.setMessage(JAASConstant.SUCCESS_MESSAGE);
						loginResponse.setUsername(user.getUsername());
						loginResponse.setToken(password);
						log.info("loginResponse: {}", loginResponse);
						return loginResponse;
					} else {
						log.info("Invalid user password");
						loginResponse.setStatusCode(401);
						loginResponse.setMessage("Invalid credentials");
						loginResponse.setUsername(user.getUsername());
						loginResponse.setToken("");
						return loginResponse;
					}
				}
			} else {
				log.info("No user found seems invalid user");
				loginResponse.setStatusCode(404);
				loginResponse.setMessage("User not found");
				loginResponse.setUsername(user.getUsername());
				loginResponse.setToken("");
				return loginResponse;
			}
		} catch (Exception e) {
			log.error("Error while validateUser service: {}", ExceptionUtils.getStackTrace(e));
		} finally {
			log.info("validateUser service completed");
		}
		return null;
	}

	@Override
	public String addUser(UserDetails user) {
		String message = null;
		try {
			log.info("calling addUser service");
			// check user exist
			if (userDao.getUserByName(user.getUsername()) != null) {
				log.info("User already exist");
				message = "User already exist";
			} else {
				// encode the password
				String encodedPassword = base64Util.endcodeString(user.getPassword());
				// encrypt the password
				user.setPassword(cryptoUtil.encrypt(encodedPassword));
				// save the user details
				boolean status = userDao.addUser(user);
				if (!status) {
					log.info("User added");
					message = "User Registration Completed";
				} else {
					log.info("Failed to add User");
					message = "User Registration Failed";
				}
			}

		} catch (Exception e) {
			log.error("Error while addUser service: {}", ExceptionUtils.getStackTrace(e));
			message = "Internal server error";
		} finally {
			log.info("addUser service completed with message : {}", message);
		}
		return message;
	}

}
