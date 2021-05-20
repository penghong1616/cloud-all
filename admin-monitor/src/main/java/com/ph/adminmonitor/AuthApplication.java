package com.ph.adminmonitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 彭宏
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
class AdminMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminMonitorApplication.class, args);
    }

}
