package com.gryfny.bcsimulator.client;

import com.gryfny.bcsimulator.client.rest.RESTConsumerService;

public class ClientRuner {

    private static RESTConsumerService restConsumerService;

    public static void main(String[] args) {
        restConsumerService.run();
    }

    public static void setRestConsumer(RESTConsumerService restConsumerService) {
        ClientRuner.restConsumerService = restConsumerService;
    }
}
