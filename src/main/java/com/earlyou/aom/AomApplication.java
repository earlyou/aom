package com.earlyou.aom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AomApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AomApplication.class);
		application.run(args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AomApplication.class);
	}

}
