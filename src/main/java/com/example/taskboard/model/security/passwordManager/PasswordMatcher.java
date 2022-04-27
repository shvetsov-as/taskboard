package com.example.taskboard.model.security.passwordManager;

import com.example.taskboard.model.security.passwordManager.core.HashGen;
import org.springframework.stereotype.Service;

@Service
public class PasswordMatcher {

    private final HashGen hashGen = new HashGen();
    private final HashSaltParser parser = new HashSaltParser();

    public boolean match(String rawPassword, String hashSaltPassword) {

        String[] hashSalt = parser.parse(hashSaltPassword);

        String hash = hashSalt[0];
        String salt = hashSalt[1];

        String password = hashGen.generateHash(salt, rawPassword);

        return password.equals(hash);
    }
}
