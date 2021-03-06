package com.lhf.sysfvsresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableDiscoveryClient
public class SysFvsResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysFvsResourcesApplication.class, args);
    }



}
