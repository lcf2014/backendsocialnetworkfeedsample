package com.backendsocialnetworkapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.SocketUtils;

import java.util.HashMap;
import java.util.Map;

@EnableFeignClients
@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class BackendSocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSocialNetworkApplication.class, args);
	}

	public static class TestApplicationInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			Map<String, Object> properties = new HashMap<>();
			properties.put("server.port", SocketUtils.findAvailableTcpPort());
			MapPropertySource propertySource = new MapPropertySource("Random Defined Server Port", properties);
			applicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
		}
	}

}
