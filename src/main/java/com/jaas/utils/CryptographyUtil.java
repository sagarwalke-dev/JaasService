package com.jaas.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sagarwalke.dev
 *
 */
@Slf4j
@Component
public class CryptographyUtil {

	@Value("secret")
	String secret;

	public String encrypt(String content) {
		try {
			SecretKeySpec secretKey = setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes("UTF-8")));
		} catch (Exception e) {
			log.error("Error in encrypt: {}", ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/*
	 * This method will decrypt the encoded content using AES algorithm
	 */
	public String decrypt(String data) {
		try {
			SecretKeySpec secretKey = setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
		} catch (Exception e) {
			log.error("Error in decrypt: {}", ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	/*
	 * This method will set the secret key for AES encryption algorithm
	 */
	/**
	 * @param secret
	 * @return
	 */
	private SecretKeySpec setKey(String secret) {
		
		MessageDigest sha = null;
		try {
			log.info("calling setKey");
			byte[] key = secret.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			return new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			log.error("Error in setKey: {}", ExceptionUtils.getStackTrace(e));
			return null;
		}
	}
}
