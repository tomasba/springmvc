package com.tb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringmvcApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringmvcApplication.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringmvcApplication.class, args);
		log.info("************");
		log.info("Context is up");
		log.info("The amount of bean instances running in the context: {}", ctx.getBeanDefinitionCount());
		log.info("************");
	}
}
