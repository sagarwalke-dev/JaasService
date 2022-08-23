package com.jaas.utils;

public class DBQueries {
	public static final String GET_USER = "SELECT USERNAME,PASSWORD FROM USER WHERE USERNAME=?";
	public static final String ADD_USER = "INSERT INTO USER (USERNAME,PASSWORD,DOB,MOBILE_NO,EMAIL,CITY) VALUES(?,?,?,?,?,?)";
}
