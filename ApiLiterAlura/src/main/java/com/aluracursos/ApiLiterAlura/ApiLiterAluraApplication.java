package com.aluracursos.ApiLiterAlura;

import com.aluracursos.ApiLiterAlura.principal.Principal;
import com.aluracursos.ApiLiterAlura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@Author Brandon Antonio Garduño Rubio
@SpringBootApplication
public class ApiLiterAluraApplication implements CommandLineRunner {
	@Autowired
	private ILibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ApiLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}
}
