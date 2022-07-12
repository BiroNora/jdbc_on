package com.norab.utils;

import com.norab.exception.InvalidInputException;

public class Utils {
    public static String addPercent(String input) throws InvalidInputException {
        if (input == null) {
            throw new InvalidInputException("Empty input");
        }
        String q = input.strip();
        if (q.isEmpty()) {
            throw new InvalidInputException("Empty input");
        }
        if (!q.startsWith("%")) {
            q = "%" + q;
        }
        if (!q.endsWith("%")) {
            q += "%";
        }
        return q;
    }
}
