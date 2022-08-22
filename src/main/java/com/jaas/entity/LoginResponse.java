package com.jaas.entity;

import lombok.Data;

/**
 * @author sagarwalke.dev
 *
 */
@Data
public class LoginResponse extends Response {

	
	private String username;
	private String token;
}
