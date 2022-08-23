package com.jaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Sagar Walke
 * @email sagarwalke7030@gmail.com
 * @date 12-Jul-2022
 */
@SpringBootApplication
@Slf4j
@EnableSwagger2
public class JaasServiceApplication {
	  
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(JaasServiceApplication.class, args);
		log.info("JassServiceApplication started successfully.");
	}

}
