package cc.young.pay.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description: SGPayApplication
 * date: 2020/9/23 13:54
 * author: faner
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FansPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FansPayApplication.class,args);
    }
}
