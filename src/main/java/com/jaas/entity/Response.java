package com.jaas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sagarwalke.dev
 *
 */
@Data
public class Response {

	private Integer statusCode;
	private String message;
}
