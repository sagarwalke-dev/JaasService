package com.jaas.utils;

import java.util.Base64;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sagarwalke.dev
 *
 */
@Slf4j
@Component
public class Base64Util {
	
	/*
	 * This method will encode the input string
	 */
	public String endcodeString(String str) {
		try {
			log.info("calling endcoding string");
			return Base64.getEncoder().encodeToString(str.getBytes());
		} catch (Exception e) {
			log.error("Error in endcodeString: {}", ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/*
	 * This method will decode the input string
	 */
	public String decodeString(String str) {
		try {
			log.info("calling decodeString");
			return new String(Base64.getDecoder().decode(str));
		} catch (Exception e) {
			log.error("Error in decodeString: {}", ExceptionUtils.getStackTrace(e));
			return null;
		}
	}
}