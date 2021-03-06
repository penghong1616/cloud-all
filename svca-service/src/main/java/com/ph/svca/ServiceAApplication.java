package com.ph.svca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 彭宏
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.ph.pojo")
@EnableJpaRepositories(basePackages = "com.ph.repository")
public class ServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }

}