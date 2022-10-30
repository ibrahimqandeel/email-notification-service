package com.usergems.notification.external.api;

import com.usergems.notification.dto.CalendarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${external.calendar.api.name}", url = "${external.calendar.api.uri}")
public interface CalendarApiClient {
    @GetMapping
    CalendarDto getEvents(@RequestHeader("Authorization") String apiKey, @RequestParam("page") int page);
}
