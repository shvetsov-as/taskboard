package com.example.taskboard.model.service.passwordManager;

import org.springframework.stereotype.Service;

@Service
public final class PasswdCheck {

    private static final int PWD_LEN = 8;

    private char ch;
    private boolean flagUpCase;
    private boolean flagLwCase;
    private boolean flagDigit;

    private void reset() {
        ch = ' ';
        flagUpCase = false;
        flagLwCase = false;
        flagDigit = false;
    }

    public boolean passwdCheck(String password) {

        if (password.trim().equals("") || password.isEmpty() || password.trim().length() < PWD_LEN) {
            return false;
        }

        password = password.trim();

        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isSpaceChar(ch)) {
                reset();
                return false;
            } else if (Character.isLowerCase(ch)) {
                flagLwCase = true;
            } else if (Character.isUpperCase(ch)) {
                flagUpCase = true;
            } else if (Character.isDigit(ch)) {
                flagDigit = true;
            }
        }

        if (flagLwCase && flagUpCase && flagDigit) {
            reset();
            return true;
        }
        return false;
    }

    public boolean passwdMatch(String password1, String password2) {
        return password1.equals(password2);
    }
}
