package com.usergems.notification.repository;

import com.usergems.notification.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<SalesEntity, Integer> {
    List<SalesEntity> findByApiKeyIsNotNull();
}
