package com.htp.controller.auth;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Authentication response with login and JWTToken")
public class AuthResponse {

    private String login;
    private String jwtToken;

}
