package com.demo.flydubai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableEurekaClient
public class FlydubaiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlydubaiServiceApplication.class, args);
	}

	 @Bean
	 @LoadBalanced
	 RestTemplate restTemplate() {
	 return new RestTemplate();
	 }

//	@Bean
//	public RemoteTokenServices remoteTokenServices() {
//
//		RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//		remoteTokenServices.setRestTemplate(restTemplate());
//		remoteTokenServices.setCheckTokenEndpointUrl("http://oauth2-service/oauth/check_token");
//		return remoteTokenServices;
//	}
//
//

}
