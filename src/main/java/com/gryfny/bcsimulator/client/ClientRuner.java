package com.gryfny.bcsimulator.client;

import com.gryfny.bcsimulator.client.configuration.MyConfig;
import com.gryfny.bcsimulator.client.rest.RESTConsumerService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@NoArgsConstructor
public class ClientRuner {

    @Autowired
    private static RESTConsumerService restConsumerService;

    public static void main(String[] args) {

        restConsumerService.run();
    }

    public static void setRestConsumer(RESTConsumerService restConsumerService) {
        ClientRuner.restConsumerService = restConsumerService;
    }
}
