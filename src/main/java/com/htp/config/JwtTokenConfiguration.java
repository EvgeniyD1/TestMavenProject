package com.htp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwttoken")
public class JwtTokenConfiguration {

    private String secret;
    private int expire;

}
