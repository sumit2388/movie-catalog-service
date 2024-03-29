package io.javabrains.moviecatlogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrixDashboard
@EnableCircuitBreaker
public class MovieCatlogServiceApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

//	@Bean
//	public  WebClient.Builder getWebClientBuilder(){
//		return WebClient.builder();
//	} 
	
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatlogServiceApplication.class, args);
	}

}
