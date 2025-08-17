package com.curso.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.curso.spring")
public class CursoSpringSts2Application {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringSts2Application.class, args);
	}

}
