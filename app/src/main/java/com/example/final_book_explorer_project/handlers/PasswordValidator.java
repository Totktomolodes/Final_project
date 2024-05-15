package com.example.final_book_explorer_project.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static boolean isPasswordStrong(String password) {
        // Проверка на длину пароля
        if (password.length() < 8) {
            return false;
        }

        // Проверка наличия как минимум одной буквы верхнего регистра
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        if (!upperCaseMatcher.find()) {
            return false;
        }

        // Проверка наличия как минимум одной буквы нижнего регистра
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
        if (!lowerCaseMatcher.find()) {
            return false;
        }

        // Проверка наличия как минимум одной цифры
        Pattern digitPattern = Pattern.compile("[0-9]");
        Matcher digitMatcher = digitPattern.matcher(password);
        return digitMatcher.find();

        // Пароль прошел все проверки
    }

//    public void CheckPassword(String password){
//        if (isPasswordStrong(password)) {
//            System.out.println("Пароль надежный.");
//        } else {
//            System.out.println("Пароль недостаточно надежный.");
//        }
//
//    }
}
