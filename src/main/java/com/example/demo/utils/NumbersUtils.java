package com.example.demo.utils;

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
