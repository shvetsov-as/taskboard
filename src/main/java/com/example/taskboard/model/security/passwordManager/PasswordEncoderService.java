package com.example.taskboard.model.security.passwordManager;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderService implements PasswordEncoder {

    private final HashSaltCollector hashSaltCollector;
    private final PasswordMatcher passwordMatcher;

    public PasswordEncoderService(HashSaltCollector hashSaltCollector, PasswordMatcher passwordMatcher) {
        this.hashSaltCollector = hashSaltCollector;
        this.passwordMatcher = passwordMatcher;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return hashSaltCollector.collect(String.valueOf(rawPassword));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordMatcher.match(String.valueOf(rawPassword), encodedPassword);
    }
}
