package com.consumer.marvel;

import com.consumer.marvel.client.CharacterClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MarvelConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelConsumerApplication.class, args);
	}

}
