package com.fatih.catan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class CatanApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatanApplication.class, args);
	}
	@GetMapping("/")
	public List<String> hello(){
		return List.of("Hello", "there");
	}

}
