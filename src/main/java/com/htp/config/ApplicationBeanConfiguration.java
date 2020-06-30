package com.htp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter getMessageConverter() {
//        return new MappingJackson2HttpMessageConverter();
//    }

}
