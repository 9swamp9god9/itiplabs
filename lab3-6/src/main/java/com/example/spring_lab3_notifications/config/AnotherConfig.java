package com.example.spring_lab3_notifications.config;

import com.example.spring_lab3_notifications.service.SmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnotherConfig {

    @Bean
    public SmsService smsService() {
        return new SmsService();
    }
}