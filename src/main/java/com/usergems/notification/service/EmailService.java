package com.usergems.notification.service;

import com.usergems.notification.dto.EmailDto;

import java.util.List;

public interface EmailService {
    void saveEmail(EmailDto emailDto);

    void updateEmailSentFlag(EmailDto emailDto);

    List<EmailDto> findAll();
}
