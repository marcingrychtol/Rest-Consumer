package com.gryfny.bcsimulator.client.configuration;

import com.gryfny.bcsimulator.client.rest.RESTConsumer;
import com.gryfny.bcsimulator.client.rest.RESTConsumerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RESTConsumer restConsumer() {
        return new RESTConsumer();
    }

    @Bean
    public RESTConsumerService restConsumerService() {
        return new RESTConsumerService();
    }

}
