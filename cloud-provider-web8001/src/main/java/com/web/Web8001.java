package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: cloudTX
 * @Package: com.web
 * @ClassName: Web8001
 * @Author: gwl
 * @Description:
 * @Date: 2020/7/28 11:15
 * @Version: 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Web8001 {
    public static void main(String[] args) {
            SpringApplication.run(Web8001.class,args);
    }
}
