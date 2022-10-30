package com.usergems.notification.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import java.util.Objects;

@Data
public class GuestInfoDto {
    @JsonAlias(value = "first_name")
    private String firstName;
    @JsonAlias(value = "last_name")
    private String lastName;

//    @JsonIgnore
//    private String email;
    private String avatar;
    private String title;
    @JsonAlias(value = "linkedin_url")
    private String linkedinUrl;
    private CompanyDto company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestInfoDto that = (GuestInfoDto) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && Objects.equals(avatar, that.avatar) && Objects.equals(title, that.title) && Objects.equals(linkedinUrl, that.linkedinUrl) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, avatar, title, linkedinUrl, company);
    }
}
