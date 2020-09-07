package com.project.offer.secondspamersender.services;


import com.project.offer.secondspamersender.entities.SpamTaskWithEmailAndNumberOfSpamerSender;
import com.project.offer.secondspamersender.tasks.SenderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.concurrent.TimeUnit;

@Service
public class ConsumerForTasksService {

    @Autowired
    private ScheduledService scheduledService;

    @Value("${spamerSender.number}")
    private int thisSpamerSenderNumber;

    @KafkaListener(topics = "${spring.kafka.senderTopic}", groupId = "${spring.kafka.groupId")
    public void receive(@Payload SpamTaskWithEmailAndNumberOfSpamerSender spamTaskWithEmailAndNumberOfSpamerSender) throws MessagingException {
        if(isThisTheRightSpamerSender(spamTaskWithEmailAndNumberOfSpamerSender.getNumberOfSpamerSender())) {
            scheduledService.getScheduledExecutorService().scheduleAtFixedRate(
                    new SenderTask(spamTaskWithEmailAndNumberOfSpamerSender),
                    0,
                    1, TimeUnit.DAYS);
        }
    }

    public boolean isThisTheRightSpamerSender(int numberOfSpamerSender){
        if(thisSpamerSenderNumber == numberOfSpamerSender) return true;
        else return false;
    }
}
