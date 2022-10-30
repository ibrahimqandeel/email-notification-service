package com.usergems.notification.external.api;

import com.usergems.notification.dto.GuestInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${external.guest.api.name}", url = "${external.guest.api.uri}")
public interface GuestInfoApiClient {

    @GetMapping("{email}")
    GuestInfoDto getGuestInfo(@RequestHeader("Authorization") String apiKey, @PathVariable String email);
}