package com.usergems.notification.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmailDto {

    private Integer senderSalesId;

    private String emailReceiver;

    private String body;

    private boolean emailSent = false;

    private Timestamp sentAt;
}
