package com.example.spring_lab3_notifications.config;
import com.example.spring_lab3_notifications.service.PushService;
import com.example.spring_lab3_notifications.service.EmailService;
import com.example.spring_lab3_notifications.service.NotificationManager;
import com.example.spring_lab3_notifications.service.SmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }

    @Bean
    public PushService pushService() {
        return new PushService();
    }

    @Bean
    public NotificationManager notificationManager() {
        return new NotificationManager(pushService());
    }
}