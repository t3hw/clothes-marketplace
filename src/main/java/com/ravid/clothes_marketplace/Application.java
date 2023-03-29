package com.ravid.clothes_marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;


@SpringBootApplication
@Log4j2
public class Application {

	public static void main(String[] args) {
        log.info( "************************************ APPLICATION STARTING *************************************");
		SpringApplication.run(Application.class, args);
        log.info( "************************************ APPLICATION LAUNCHED *************************************");
	}

}
