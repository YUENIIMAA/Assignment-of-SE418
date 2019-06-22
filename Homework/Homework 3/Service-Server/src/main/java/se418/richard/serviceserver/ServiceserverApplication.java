package se418.richard.serviceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceserverApplication.class, args);
	}

}
