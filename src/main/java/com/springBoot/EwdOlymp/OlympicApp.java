package com.springBoot.EwdOlymp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import perform.PerformRestExample;
import validator.NieuweWedstrijdValidation;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "repository" })
@EntityScan("domain")
@ComponentScan({ "service", "com.springBoot.EwdOlymp" })
public class OlympicApp implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OlympicApp.class, args);
		try {
			new PerformRestExample();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
		registry.addViewController("/access-denied").setViewName("access-denied");
	}

	@Bean
	NieuweWedstrijdValidation nieuweWedstrijdValidation() {
		return new NieuweWedstrijdValidation();
	}

}