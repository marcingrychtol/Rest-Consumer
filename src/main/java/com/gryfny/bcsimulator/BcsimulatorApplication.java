package com.gryfny.bcsimulator;

import com.gryfny.bcsimulator.client.configuration.MyConfig;
import com.gryfny.bcsimulator.client.service.RESTConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.ResourceAccessException;


public class BcsimulatorApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BcsimulatorApplication.class);

    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        RESTConsumerService restConsumerService = (RESTConsumerService) ctx.getBean("restConsumerService");

        while (true) {
            try {
                restConsumerService.run();
            } catch (ResourceAccessException e) {
                int reconnectTime = 5;
                LOG.warn("Node inactive. Waiting for connection...");
                for (int i = reconnectTime; i >0 ; i--) {
                    LOG.warn("...reconnect in "+i+"s");
                    Thread.sleep(1000);
                }
            }
        }
    }

}
