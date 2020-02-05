package com.gryfny.bcsimulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BcsimulatorApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BcsimulatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BcsimulatorApplication.class, args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }


}
