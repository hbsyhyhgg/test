package com.jq.framework.message;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableFeignClients(basePackages = {"com.jq.framework.message"})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.jq.framework.core",
        "com.jq.framework.message"
})
public class Application {

	public static void main(String[] args) {
	   new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
