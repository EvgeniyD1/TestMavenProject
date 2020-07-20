package com.htp.controller.springdata.users;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@ApiModel(description = "User creation model")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserSDSaveRequest {

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "user first name")
    private String username;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "user last name")
    private String surname;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "user patronymic")
    private String patronymic;

    @Size(max = 15)
    @ApiModelProperty(dataType = "string", notes = "user phone number")
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "user login")
    private String login;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(required = true, dataType = "string", notes = "user password")
    private String password;

    @ApiModelProperty(dataType = "date", notes = "user birth date")
    private Date birthDate;

    @Email
    @Size(max = 100)
    @ApiModelProperty(dataType = "string", notes = "user email")
    private String mail;

    @Size(max = 100)
    @ApiModelProperty(dataType = "string", notes = "user country location")
    private String countryLocation;
}
