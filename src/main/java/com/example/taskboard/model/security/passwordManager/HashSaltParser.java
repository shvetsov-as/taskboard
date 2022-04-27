package com.example.taskboard.model.security.passwordManager;

import org.springframework.stereotype.Service;

@Service
public class HashSaltParser {

    private final static String DELIMITER = ":";

    public String merge (String hash, String salt){

        StringBuilder sb = new StringBuilder();
        sb.append(hash);
        sb.append(DELIMITER);
        sb.append(salt);
        String result = sb.toString();
        sb.delete(0, result.length());
        return result;
    }

    public String[] parse (String hashSalt){
        return hashSalt.split(DELIMITER);
    }
}
