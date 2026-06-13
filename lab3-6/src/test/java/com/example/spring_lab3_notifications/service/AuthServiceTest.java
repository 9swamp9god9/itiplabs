package com.example.spring_lab3_notifications.service;

import com.example.spring_lab3_notifications.model.dto.RegisterRequest;
import com.example.spring_lab3_notifications.model.entity.User;
import com.example.spring_lab3_notifications.model.enums.UserRole;
import com.example.spring_lab3_notifications.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUser() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Иван");
        request.setEmail("ivan@example.com");
        request.setPassword("qwerty123");

        when(userRepository.findByEmail("ivan@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("qwerty123")).thenReturn("hashed_password");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        authService.register(request);

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("qwerty123");
    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setName("Иван");
        request.setEmail("ivan@example.com");
        request.setPassword("qwerty123");

        when(userRepository.findByEmail("ivan@example.com"))
                .thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any());
    }
}