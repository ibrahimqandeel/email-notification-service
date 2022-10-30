package com.usergems.notification.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class CompanyDto {
    @JsonAlias(value = "name")
    private String companyName;

    @JsonAlias(value = "linkedin_url")
    private String linkedinUrl;

    private int employees;

}
