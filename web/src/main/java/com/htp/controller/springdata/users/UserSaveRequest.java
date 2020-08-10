package com.htp.controller.springdata.users;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "User creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserSaveRequest {

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "name", dataType = "string", notes = "user first name")
    private String username;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "surname", dataType = "string", notes = "user last name")
    private String surname;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "patronymic", dataType = "string", notes = "user patronymic")
    private String patronymic;

    @Size(max = 15)
    @ApiModelProperty(dataType = "string", example = "88006663636", notes = "user phone number")
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "login", dataType = "string", notes = "user login")
    private String login;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, example = "password", dataType = "string", notes = "user password")
    private String password;

    @ApiModelProperty(dataType = "date", example = "2020-07-22", notes = "user birth date")
    private Date birthDate;

    @Email
    @Size(max = 100)
    @ApiModelProperty(dataType = "string", example = "mail@mail.ru", notes = "user email")
    private String mail;

    @Size(max = 100)
    @ApiModelProperty(dataType = "string", example = "Belarus", notes = "user country location")
    private String countryLocation;
}
