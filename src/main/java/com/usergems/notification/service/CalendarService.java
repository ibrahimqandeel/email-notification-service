package com.usergems.notification.service;

import com.usergems.notification.dto.EventDto;

import java.util.List;
import java.util.Map;

public interface CalendarService {
    List<EventDto> getTodayEventsBySalesApiKey(String salesApiKey);
}
