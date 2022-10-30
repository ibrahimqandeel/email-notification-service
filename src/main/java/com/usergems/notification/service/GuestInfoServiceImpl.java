package com.usergems.notification.service;

import com.usergems.notification.dto.GuestInfoDto;
import com.usergems.notification.entity.GuestInfoEntity;
import com.usergems.notification.external.api.GuestInfoApiClient;
import com.usergems.notification.repository.GuestInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GuestInfoServiceImpl implements GuestInfoService {

    @Value("${external.guest.api.key}")
    private String apiKey;
    private final GuestInfoRepository guestInfoRepository;
    private final GuestInfoApiClient guestInfoApiClient;
    private final ModelMapper modelMapper;

    public GuestInfoServiceImpl(GuestInfoRepository guestInfoRepository, GuestInfoApiClient guestInfoApiClient, ModelMapper modelMapper) {
        this.guestInfoRepository = guestInfoRepository;
        this.guestInfoApiClient = guestInfoApiClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveGuestInfo(GuestInfoDto guestInfo) {
        guestInfoRepository.save(modelMapper.map(guestInfo, GuestInfoEntity.class));
    }

    @Override
    @Cacheable(value = "guestInfo", key = "#email")
    public Optional<GuestInfoDto> getGuestInfoByEmail(String email) {
        GuestInfoDto guestInfo = findGuestInfoUpdatedWithin30Days(email);
        if (guestInfo == null) {
            guestInfo = getGuestInfoFromExternalApi(email);
        }

        if (guestInfo != null && guestInfo.getFirstName() != null) {
            return Optional.of(guestInfo);
        }
        return Optional.empty();
    }

    private GuestInfoDto findGuestInfoUpdatedWithin30Days(String email) {
        return modelMapper.map(guestInfoRepository.findByUpdatedAtLessThanEqualAndEmail(LocalDateTime.now(), email), GuestInfoDto.class);
    }

    private GuestInfoDto getGuestInfoFromExternalApi(String email) {
        return guestInfoApiClient.getGuestInfo(apiKey, email);
    }
}
