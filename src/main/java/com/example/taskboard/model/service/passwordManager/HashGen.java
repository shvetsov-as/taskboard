package com.example.taskboard.model.service.passwordManager;

import com.example.taskboard.model.service.passwordManager.core.HashCore;
import com.example.taskboard.model.service.passwordManager.core.SaltGen;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public final class HashGen {

    private final SaltGen saltGen;
    private final HashCore hashCore;

    private String passwordPassed;
    private String salt;

    public HashGen(SaltGen saltGen, HashCore hashCore) {
        this.saltGen = saltGen;
        this.hashCore = hashCore;
    }

    public Map<String, String> hashGen(String password) throws NoSuchAlgorithmException {
        salt = saltGen.saltGenerated();
        passwordPassed = hashCore.getHash(salt, password);

        Map<String, String> saltHashMapRes = new HashMap<>();
        saltHashMapRes.put("salt", salt);
        saltHashMapRes.put("hash", passwordPassed);

        return saltHashMapRes;

    }
}
