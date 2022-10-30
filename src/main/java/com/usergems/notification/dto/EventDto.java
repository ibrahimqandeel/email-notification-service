package com.usergems.notification.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventDto {
    private int id;
    private String changed;
    private String start;
    private String end;
    private String title;
    private List<String> accepted;
    private List<String> rejected;
}
