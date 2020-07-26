package com.htp.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("cache");
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter getMessageConverter() {
//        return new MappingJackson2HttpMessageConverter();
//    }

}
