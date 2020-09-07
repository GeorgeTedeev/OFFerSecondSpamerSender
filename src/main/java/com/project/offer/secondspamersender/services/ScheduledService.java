package com.project.offer.secondspamersender.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;

@Service
public class ScheduledService {

    private ScheduledExecutorService scheduledExecutorService;

    public ScheduledService() {
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }
}
