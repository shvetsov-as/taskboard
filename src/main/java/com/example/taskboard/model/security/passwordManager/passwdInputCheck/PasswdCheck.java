package com.example.taskboard.model.security.passwordManager.passwdInputCheck;

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

        password = password.trim().replaceAll(" ", "");

        if (password.length() < PWD_LEN) {
            return false;
        }

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
