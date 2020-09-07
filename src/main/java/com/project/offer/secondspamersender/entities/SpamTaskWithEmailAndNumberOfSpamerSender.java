package com.project.offer.secondspamersender.entities;

import org.springframework.stereotype.Component;

@Component
public class SpamTaskWithEmailAndNumberOfSpamerSender {
    private SpamTask spamTask;
    private String email;
    private int numberOfSpamerSender;

    public SpamTaskWithEmailAndNumberOfSpamerSender(SpamTask spamTask, String email, int numberOfSpamerSender) {
        this.spamTask = spamTask;
        this.email = email;
        this.numberOfSpamerSender = numberOfSpamerSender;
    }

    public SpamTask getSpamTask() {
        return spamTask;
    }

    public void setSpamTask(SpamTask spamTask) {
        this.spamTask = spamTask;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfSpamerSender() {
        return numberOfSpamerSender;
    }

    public void setNumberOfSpamerSender(int numberOfSpamerSender) {
        this.numberOfSpamerSender = numberOfSpamerSender;
    }
}
