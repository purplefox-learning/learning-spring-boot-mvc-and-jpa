package com.ycm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		//a tomcat server will be listening on port 8080

		SpringApplication.run(Application.class,args);
	}
}

