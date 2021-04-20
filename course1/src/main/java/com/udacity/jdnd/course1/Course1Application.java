package com.udacity.jdnd.course1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Clock;

@SpringBootApplication
public class Course1Application {

	public static void main(String[] args) {
		SpringApplication.run(Course1Application.class, args);
		System.out.println("starting");
	}

}
