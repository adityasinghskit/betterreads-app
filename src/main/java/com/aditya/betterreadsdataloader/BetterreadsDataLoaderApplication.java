package com.aditya.betterreadsdataloader;

import com.datastax.oss.driver.api.core.CqlSession;
import connection.DataStaxAstraPropeties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraPropeties.class)
public class BetterreadsDataLoaderApplication {

	@Value("${spring.data.cassandra.username}")
	private String username;
	@Value("${spring.data.cassandra.password}")
	private String password;

	public static void main(String[] args) {
		SpringApplication.run(BetterreadsDataLoaderApplication.class, args);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraPropeties astraPropeties) {
//		Path bundle = astraPropeties.getSecureConnectBundle().toPath();
//		return builder -> builder.withCloudSecureConnectBundle(bundle);
		return builder -> builder
				.withCloudSecureConnectBundle(Paths.get("src/main/resources/secure-connect.zip"))
				.withAuthCredentials(username, password);
	}

}
