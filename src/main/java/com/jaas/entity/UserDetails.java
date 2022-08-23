package com.jaas.entity;

import java.sql.Date;

import lombok.Data;

/**
 * @author sagarwalke.dev
 *
 */
@Data
public class UserDetails {

	private String username;
	private String password;
	private Date dob;
	private String city;
	private String email;
	private String mobileNo;
}
