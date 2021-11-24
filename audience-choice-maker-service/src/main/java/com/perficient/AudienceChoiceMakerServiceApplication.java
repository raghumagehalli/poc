package com.perficient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableScheduling
@EnableHystrix
@EnableHystrixDashboard
@OpenAPIDefinition
(info = @Info(title = "AudienceChoice", version = "v1", description = "APIs for recording and processing audience choice", 
license = @License(name = "Open Api 3.0", url = "http://foo.bar"),
contact = @Contact(url = "https://www.perficient.com/", name = "Raghu Magehalli Krishnegowda", email = "raghu.magehalli@perficient.com")))
public class AudienceChoiceMakerServiceApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();

	}

	public static void main(String[] args) {
		SpringApplication.run(AudienceChoiceMakerServiceApplication.class, args);
	}

}
