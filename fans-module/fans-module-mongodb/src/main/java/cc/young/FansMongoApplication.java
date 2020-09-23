package cc.young;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: FansSystemApplication
 * date: 2020/9/18 17:32
 * author: faner
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FansMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FansMongoApplication.class, args);
    }
}