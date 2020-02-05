package com.gryfny.bcsimulator;

import com.gryfny.bcsimulator.client.configuration.MyConfig;
import com.gryfny.bcsimulator.client.rest.RESTConsumerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class BcsimulatorApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        RESTConsumerService restConsumerService = (RESTConsumerService) ctx.getBean("restConsumerService");
        restConsumerService.run();

    }

}
