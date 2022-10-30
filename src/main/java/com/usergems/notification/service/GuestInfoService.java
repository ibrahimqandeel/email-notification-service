package com.usergems.notification.service;

import com.usergems.notification.dto.GuestInfoDto;

import java.util.Optional;

public interface GuestInfoService {
    void saveGuestInfo(GuestInfoDto guestInfo);
    Optional<GuestInfoDto> getGuestInfoByEmail(String email);
}
