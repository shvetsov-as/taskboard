package com.example.taskboard.model.service.passwordManager;

import com.example.taskboard.model.service.passwordManager.core.HashCore;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public class HashCheck {

    private final HashCore hashCore;

    private String passwordPassed;
    private String salt;

    public HashCheck(HashCore hashCore) {
        this.hashCore = hashCore;
    }

    public boolean hashCheck(Map<String, String> salt_hash, String password) throws NoSuchAlgorithmException {

        salt = salt_hash.get("salt");
        passwordPassed = hashCore.getHash(salt, password);

        return passwordPassed.equals(salt_hash.get("hash"));//hash 128 for SHA-512 (2M - 1 min 40 sec); hash 64 for SHA-256 (2M - 52 sec)
    }
}
