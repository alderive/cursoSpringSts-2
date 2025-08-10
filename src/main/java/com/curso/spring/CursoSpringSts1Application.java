package com.curso.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.curso.spring")
public class CursoSpringSts1Application {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringSts1Application.class, args);
	}

}
