package com.usergems.notification.repository;

import com.usergems.notification.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity, Integer> {
}
