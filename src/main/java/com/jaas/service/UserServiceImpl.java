package com.jaas.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaas.dao.UserDao;
import com.jaas.entity.LoginResponse;
import com.jaas.entity.User;
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
			User dbUser = userDao.getUser(user);
			log.info("user details from DB : {}", dbUser);
			if (dbUser.getUsername() != null) {
				log.info("User found in DB : {}", dbUser.getUsername());
				// decrypt the password
				String decryped = cryptoUtil.decrypt(dbUser.getPassword());
				// decode the password
				String password = base64Util.decodeString(decryped);
				if (password != null && dbUser.getPassword() != null) {
					log.info("validating password");
					if (password.equals(dbUser.getPassword())) {
						log.info("Valid user");
						loginResponse.setStatusCode(JAASConstant.SUCCESS_CODE);
						loginResponse.setMessage(JAASConstant.SUCCESS_MESSAGE);
						loginResponse.setUsername(user.getUsername());
						loginResponse.setToken(password);
						return loginResponse;
					}
				}
			} else {
				log.info("No user found seems invalid user");
				loginResponse.setStatusCode(401);
				loginResponse.setMessage("Invalid credentials");
				loginResponse.setUsername(user.getUsername());
				loginResponse.setToken("");
				return loginResponse;
			}
		} catch (Exception e) {
			log.error("Error while validateUser service: {}", ExceptionUtils.getStackTrace(e));
		}
		return null;
	}

}
