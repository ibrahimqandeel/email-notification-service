package com.usergems.notification.scheduler;

import com.usergems.notification.service.EmailService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class EmailNotificationScheduler {

    private final EmailService emailService;

    public EmailNotificationScheduler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 7 59 * * ?")
    public void run(){
        //emailService.findUnSentEmails();
        //Send Emails
        //emailService.updateEmailSentFlag();
    }
}
