package com.example.spring_lab3_notifications.service;

import com.example.spring_lab3_notifications.model.dto.NotificationDto;
import com.example.spring_lab3_notifications.model.entity.Notification;
import com.example.spring_lab3_notifications.model.entity.User;
import com.example.spring_lab3_notifications.model.enums.NotificationChannel;
import com.example.spring_lab3_notifications.repository.NotificationRepository;
import com.example.spring_lab3_notifications.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldCreateNotification() {
        User user = new User();
        user.setId(1L);
        user.setEmail("ivan@example.com");

        NotificationDto dto = NotificationDto.builder()
                .title("Напоминание")
                .message("Завтра пара по Spring")
                .channel(NotificationChannel.EMAIL)
                .recipientId(1L)
                .build();

        Notification savedNotification = new Notification();
        savedNotification.setTitle(dto.getTitle());
        savedNotification.setMessage(dto.getMessage());
        savedNotification.setChannel(dto.getChannel());
        savedNotification.setRecipient(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);

        Notification result = notificationService.createNotification(dto);

        assertNotNull(result);
        assertEquals("Напоминание", result.getTitle());
        assertEquals(NotificationChannel.EMAIL, result.getChannel());
        assertEquals(user, result.getRecipient());
    }

    // Самостоятельное задание 3: тест getNotificationById
    @Test
    void shouldGetNotificationById() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setTitle("Тест");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        Notification result = notificationService.getNotificationById(1L);

        assertNotNull(result);
        assertEquals("Тест", result.getTitle());
        verify(notificationRepository, times(1)).findById(1L);
    }

    // Самостоятельное задание 4: негативный тест — уведомление не найдено
    @Test
    void shouldThrowWhenNotificationNotFound() {
        when(notificationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                notificationService.getNotificationById(99L));
    }
}