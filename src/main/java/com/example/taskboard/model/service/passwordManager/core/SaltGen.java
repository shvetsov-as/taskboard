package com.example.taskboard.model.service.passwordManager.core;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public final class SaltGen {

    private static final int SALT_LEN = 10;
    private static final int UPPER_CASE_BOUND_MAX = 90;//UTF-8
    private static final int UPPER_CASE_BOUND_MIN = 65;
    private static final int LOWER_CASE_BOUND_MAX = 122;
    private static final int LOWER_CASE_BOUND_MIN = 97;

    private char ch;
    private String strSalt;

    private Random random = new Random();
    private StringBuilder saltBuilder = new StringBuilder();


    public String saltGenerated() {
        for (int i = 0; i < SALT_LEN; i++) {
            do {
                ch = (char) random.nextInt(123);
            } while ((ch >= UPPER_CASE_BOUND_MIN) ^ (ch <= UPPER_CASE_BOUND_MAX) && (ch >= LOWER_CASE_BOUND_MIN) ^ (ch <= LOWER_CASE_BOUND_MAX));
            saltBuilder.append(ch);
        }
        strSalt = saltBuilder.toString();
        saltBuilder.delete(0, strSalt.length());
        return strSalt;
    }
}
