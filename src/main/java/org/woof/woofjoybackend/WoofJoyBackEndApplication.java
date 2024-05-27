package org.woof.woofjoybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EntityScan
public class WoofJoyBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(WoofJoyBackEndApplication.class, args);
	}

}
