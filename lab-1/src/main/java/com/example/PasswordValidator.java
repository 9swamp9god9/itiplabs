package com.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        try {
            if (password.length() < 8 || password.length() > 16) {
                System.out.println("Ошибка: пароль должен быть от 8 до 16 символов");
                return;
            }

            Pattern pattern1 = Pattern.compile("^[A-Za-z0-9]+$");
            Matcher matcher1 = pattern1.matcher(password);
            if (!matcher1.matches()) {
                System.out.println("Ошибка: только латинские буквы и цифры");
                return;
            }

            Pattern pattern2 = Pattern.compile("[A-Z]");
            if (!pattern2.matcher(password).find()) {
                System.out.println("Ошибка: нужна заглавная буква");
                return;
            }

            Pattern pattern3 = Pattern.compile("[0-9]");
            if (!pattern3.matcher(password).find()) {
                System.out.println("Ошибка: нужна цифра");
                return;
            }

            System.out.println("Пароль корректен");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}