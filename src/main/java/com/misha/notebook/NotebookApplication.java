package com.misha.notebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = {"com.misha.notebook.Repository"})
@ComponentScan("com.misha.notebook")
@SpringBootApplication
public class NotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}
}
