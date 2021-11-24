package com.perficient.filmhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition
(info = @Info(title = "FilmHouse", version = "v1", description = "APIs for in house movie database", 
license = @License(name = "Open Api 3.0", url = "http://foo.bar"),
contact = @Contact(url = "https://www.perficient.com/", name = "Raghu Magehalli Krishnegowda", email = "raghu.magehalli@perficient.com")))
public class FilmhouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmhouseApplication.class, args);
	}

}
