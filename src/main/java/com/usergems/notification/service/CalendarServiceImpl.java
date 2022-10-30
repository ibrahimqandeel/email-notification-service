package com.usergems.notification.service;

import com.usergems.notification.dto.CalendarDto;
import com.usergems.notification.dto.EventDto;
import com.usergems.notification.external.api.CalendarApiClient;
import com.usergems.notification.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarApiClient calendarApiClient;

    public CalendarServiceImpl(CalendarApiClient calendarApiClient) {
        this.calendarApiClient = calendarApiClient;
    }

    @Override
    public List<EventDto> getTodayEventsBySalesApiKey(String apiKey) {
        List<EventDto> todayEvents = new ArrayList<>();
        Date todayDate = DateUtil.formatTodayDate("yyyy-MM-dd");

        int page = 1;
        boolean noMoreEventsForToday = false;
        CalendarDto apiResponse;
        do {
            apiResponse = calendarApiClient.getEvents(apiKey, page);

            if (apiResponse.getTotal() > 0 && apiResponse.getEvents().size() > 0) {

                for (int i = 0; i < apiResponse.getEvents().size(); i++) {
                    EventDto event = apiResponse.getEvents().get(i);
                    Date eventStartDate = DateUtil.convertToDateFromString(event.getStart(), "yyyy-MM-dd");
                    if (eventStartDate.before(todayDate)) {
                        noMoreEventsForToday = true;
                        break;
                    } else if (eventStartDate.equals(todayDate)) {
                        todayEvents.add(event);
                    }
                }
            } else {
                noMoreEventsForToday = true;
            }

            page++;
        } while (!noMoreEventsForToday);

        return todayEvents;
    }
}
