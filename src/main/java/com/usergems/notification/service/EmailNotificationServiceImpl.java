package com.usergems.notification.service;

public class EmailNotificationServiceImpl implements NotificationService {

    private final EmailService emailService;

    public EmailNotificationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendEmailNotification() {
    }

}
