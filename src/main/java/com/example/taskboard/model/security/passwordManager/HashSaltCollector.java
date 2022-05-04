package com.example.taskboard.model.security.passwordManager;


import com.example.taskboard.model.security.passwordManager.core.HashGen;
import com.example.taskboard.model.security.passwordManager.core.SaltGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashSaltCollector {

    private final SaltGen saltGen = new SaltGen();
    private final HashGen hashGen = new HashGen();

    @Autowired
    private HashSaltParser parser;

    private String hash;
    private String salt;

    public String collect(String password) {

        salt = saltGen.generateSalt();
        hash = hashGen.generateHash(salt, password);

        return parser.merge(hash, salt);
    }
}
