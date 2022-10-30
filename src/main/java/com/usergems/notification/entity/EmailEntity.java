package com.usergems.notification.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = EmailEntity.TABLE_NAME)
@Entity(name = EmailEntity.ENTITY_NAME)

//@Builder
//@RequiredArgsConstructor
@Data
public class EmailEntity {
    public static final String TABLE_NAME = "email";
    public static final String ENTITY_NAME = "EmailEntity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

//    @Column(name = "sales_id", nullable = false)
//    private Integer salesId;

    @Column(name = "email_receiver", nullable = false)
    private String emailReceiver;

    @Column(name = "body", nullable = false, columnDefinition="TEXT")
    private String body;

    @Column(name = "email_sent", nullable = false)
    private boolean emailSent = false;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    @Column(name = "send_before")
    private Timestamp sendBefore;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
