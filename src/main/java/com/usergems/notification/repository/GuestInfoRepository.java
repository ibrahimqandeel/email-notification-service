package com.usergems.notification.repository;

import com.usergems.notification.entity.GuestInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface GuestInfoRepository extends JpaRepository<GuestInfoEntity, Integer> {
    GuestInfoEntity findByUpdatedAtLessThanEqualAndEmail(LocalDateTime date, String email);
}
