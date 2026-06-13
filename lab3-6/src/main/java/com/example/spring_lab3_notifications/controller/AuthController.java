package com.example.spring_lab3_notifications.controller;

import com.example.spring_lab3_notifications.model.dto.LoginRequest;
import com.example.spring_lab3_notifications.model.dto.RegisterRequest;
import com.example.spring_lab3_notifications.security.JwtService;
import com.example.spring_lab3_notifications.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return "Пользователь успешно зарегистрирован";
    }

    // Самостоятельное задание 1: endpoint регистрации администратора
    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody @Valid RegisterRequest request) {
        authService.registerAdmin(request);
        return "Администратор успешно зарегистрирован";
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request) {
        // Самостоятельное задание 3: обработка ошибки при неверном логине
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return "Неверный email или пароль";
        }
        return jwtService.generateToken(request.getEmail());
    }
}