package com.htp.controller.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ApiModel(description = "Authentication request with login and password")
public class AuthRequest implements Serializable {

    @NotEmpty
    @ApiModelProperty(required = true, allowableValues = "ED", dataType = "string", notes = "login")
    private String username;

    @NotEmpty
    @ApiModelProperty(required = true, allowableValues = "EDp", dataType = "string", notes = "password")
    private String password;
}
