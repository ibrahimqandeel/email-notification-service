package com.usergems.notification.service;

import com.usergems.notification.dto.SalesDto;

import java.util.List;
import java.util.Optional;

public interface SalesService {
    List<SalesDto> findAllWhenApiKeyNotEmpty();
}
