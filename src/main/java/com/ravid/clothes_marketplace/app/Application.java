package com.ravid.clothes_marketplace.app;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Log4j2
public class Application {

	public static void main(String[] args) {
        log.info( "*********************************** APPLICATION STARTING ************************************");
		SpringApplication.run(Application.class, args);
        log.info( "*********************************** APPLICATION LAUNCHED ************************************");
	}

}
