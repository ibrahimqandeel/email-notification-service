package com.usergems.notification.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = CompanyEntity.TABLE_NAME)
@Entity(name = CompanyEntity.ENTITY_NAME)
@Data
public class CompanyEntity {
    public static final String TABLE_NAME = "company";
    public static final String ENTITY_NAME = "CompanyEntity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "company_name", updatable = false, nullable = false)
    private String companyName;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "employees")
    private int employees;
}
