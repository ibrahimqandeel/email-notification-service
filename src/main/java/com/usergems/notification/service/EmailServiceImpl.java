package com.usergems.notification.service;

import com.usergems.notification.dto.EmailDto;
import com.usergems.notification.entity.EmailEntity;
import com.usergems.notification.repository.EmailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final ModelMapper modelMapper;

    public EmailServiceImpl(EmailRepository emailRepository, ModelMapper modelMapper) {
        this.emailRepository = emailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveEmail(EmailDto emailDto) {
        emailRepository.save(modelMapper.map(emailDto, EmailEntity.class));
    }

    @Override
    public void updateEmailSentFlag(EmailDto emailDto) {
        emailRepository.save(modelMapper.map(emailDto, EmailEntity.class));
    }

    @Override
    public List<EmailDto> findAll() {
        return modelMapper.map(emailRepository.findAll(), List.class);
    }
}
