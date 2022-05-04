package com.example.taskboard.model.security.passwordManager.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashGen {

    private static final int PASSES = 10;//number of hash passes in function
    private static final int SALT_START = 0;//salt first char
    private static final int SALT_LEN = 5;//salt middle char(full salt - ten characters)

    private StringBuilder hashBuilder = new StringBuilder();
    private MessageDigest sha2;// = MessageDigest.getInstance("SHA-2");

    private byte[] hashBytes;
    private String passwordHash; //complited password hash

    public String generateHash(String salt, String password) {

        hashBuilder.append(salt, SALT_START, SALT_LEN);// get a half of salt
        hashBuilder.append(password.trim().replace(" ", ""));//delete all whitespaces
        passwordHash = hashBuilder.toString();
        System.out.println(passwordHash);
        hashBuilder.delete(0, passwordHash.length());//hashBuilder refresh.

        try {
            sha2 = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int count = 0;
        do {
            hashBytes = sha2.digest(passwordHash.getBytes());
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02X", b));
            }
            passwordHash = hashBuilder.toString();
            hashBuilder.delete(0, passwordHash.length());//hashBuilder refresh.
            count++;
        } while (count != PASSES);

        return passwordHash;
    }
}
