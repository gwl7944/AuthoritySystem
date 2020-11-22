package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: cloudTX
 * @Package: com.web
 * @ClassName: Main80
 * @Author: gwl
 * @Description:
 * @Date: 2020/7/31 15:50
 * @Version: 1.0
 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ServletComponentScan
public class Main8868 {
    public static void main(String[] args) {
        SpringApplication.run(Main8868.class,args);
    }
}
