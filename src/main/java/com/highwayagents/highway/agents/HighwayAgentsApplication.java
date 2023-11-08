package com.highwayagents.highway.agents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class HighwayAgentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighwayAgentsApplication.class, args);
	}

}
