package com.project.offer.secondspamersender.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class ConsumerForChangeableTask {

    @Autowired
    ScheduledService scheduledService;

    @KafkaListener(topics = "${spring.kafka.taskChanged}")
    public void receive(@Payload String message){
        scheduledService.getScheduledExecutorService().shutdownNow();
        scheduledService.setScheduledExecutorService(new ScheduledThreadPoolExecutor(10));
    }
}
