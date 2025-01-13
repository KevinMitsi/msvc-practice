package org.kevinmitsi.OAuth.msvc_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcSecurityApplication.class, args);
	}

}
