package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User{
    private Long id;
    private String username;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String login;
    private String password;
    private Timestamp created;
    private Timestamp changed;
    private Date birthDate;
    private boolean isBlocked;
    private String mail;
    private String countryLocation;
    private Role role;

}