package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserRequest {

    private String username;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String login;
    private String password;
    private Date birthDate;
    private boolean isBlocked;
    private String mail;
    private String countryLocation;
}
