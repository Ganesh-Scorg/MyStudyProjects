package com.manufactry.surya.AutoServiceProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = { "com.manufactry.surya.AutoServiceProvider.controller",
		"com.manufactry.surya.AutoServiceProvider.service" } )
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class AutoServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoServiceProviderApplication.class, args);
	}

}
