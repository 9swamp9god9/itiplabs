package org.example;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Программа запущена");

        try (InputStream is = Main.class.getClassLoader()
                .getResourceAsStream("build-passport.properties")) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                logger.info("Сборка выполнена пользователем: {}", props.getProperty("build.user"));
                logger.info("Дата сборки: {}", props.getProperty("build.date"));
                logger.info("Сообщение: {}", props.getProperty("build.message"));
            }
        } catch (Exception e) {
            logger.warn("Не удалось прочитать build-passport.properties");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        String input = scanner.nextLine();

        String reversed = StringUtils.reverse(input);
        String capitalized = StringUtils.capitalize(input);

        logger.info("Исходная строка: {}", input);
        logger.info("Перевёрнутая: {}", reversed);
        logger.info("С заглавной буквы: {}", capitalized);

        logger.info("Программа завершена");
    }
}