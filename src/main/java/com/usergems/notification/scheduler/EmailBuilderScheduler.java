package com.usergems.notification.scheduler;

import com.usergems.notification.service.EmailBuilderService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@EnableScheduling
public class EmailBuilderScheduler {

    private final EmailBuilderService emailBuilderService;

    public EmailBuilderScheduler(EmailBuilderService emailBuilderService) {
        this.emailBuilderService = emailBuilderService;
    }

    @Scheduled(cron = "0 6 * * * ?")
    public void run(){
        emailBuilderService.buildTodayEmails();
    }
}
