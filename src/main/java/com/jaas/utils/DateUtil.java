package com.jaas.utils;

import java.sql.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateUtil {
	public Date convertToDate(String date) {
		Date sqlDate=null;
		try {
			log.info("calling convertToDate from util");
			log.info("converting date to SQL date: {}",date);
			sqlDate=new Date(new java.util.Date(date).getTime());
		} catch (Exception e) {
			log.error("Error in convertToDate util: {}",ExceptionUtils.getStackTrace(e));
		}
		return sqlDate;
	}
}
