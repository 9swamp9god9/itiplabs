package com.example.spring_lab3_notifications;

import com.example.spring_lab3_notifications.config.AnotherConfig;
import com.example.spring_lab3_notifications.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, AnotherConfig.class})
public class SpringLab3NotificationsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringLab3NotificationsApplication.class, args);
	}
}