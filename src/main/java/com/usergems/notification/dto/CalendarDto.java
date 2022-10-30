package com.usergems.notification.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class CalendarDto {
    private int total;

    @JsonAlias(value = "per_page")
    private int perPage;

    @JsonAlias(value = "current_page")
    private String currentPage;

    @JsonAlias(value = "data")
    private List<EventDto> events;
}
