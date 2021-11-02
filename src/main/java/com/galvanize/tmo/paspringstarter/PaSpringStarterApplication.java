package com.galvanize.tmo.paspringstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PaSpringStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaSpringStarterApplication.class, args);
	}

	@GetMapping("/hello")
public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    //Console log the name to determine the paramter passed when default is implemented
    System.out.println(name);
    return String.format("Hello %s!", name);
}
	

}
