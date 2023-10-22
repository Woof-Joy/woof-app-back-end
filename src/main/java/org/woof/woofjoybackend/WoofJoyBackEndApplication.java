package org.woof.woofjoybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WoofJoyBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoofJoyBackEndApplication.class, args);
//	MUDO AQUI
	}

}
