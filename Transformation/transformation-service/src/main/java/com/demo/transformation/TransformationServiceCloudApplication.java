package com.demo.transformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableEurekaClient
//@EntityScan("com.us.models.*")
//@EntityScan(basePackages= {"com.demo.transformation.model.Transformationmodule","com.demo.transformation.model.Utility"})
public class TransformationServiceCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransformationServiceCloudApplication.class, args);
	}

	
	@Bean
	//@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
