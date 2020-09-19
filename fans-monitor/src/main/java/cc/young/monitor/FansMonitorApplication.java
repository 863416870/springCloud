package cc.young.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: SGMonitorApplication
 * date: 2020/9/18 17:32
 * author: faner
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class FansMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(FansMonitorApplication.class, args);
    }

}