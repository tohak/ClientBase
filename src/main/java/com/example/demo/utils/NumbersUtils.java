package com.example.demo.utils;

/*
 * Ультильный класс для проверки являеться ли строка числом
 */
public class NumbersUtils {
    private NumbersUtils() {
    }
    public static boolean isDigit(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
