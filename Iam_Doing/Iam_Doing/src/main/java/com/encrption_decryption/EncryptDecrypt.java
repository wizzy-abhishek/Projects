package com.encrption_decryption;


public class EncryptDecrypt {

    private final int key = 6 ;

    public String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                int shiftedChar = c + key;

                if (Character.isUpperCase(c) && shiftedChar > 'Z') {
                    shiftedChar = shiftedChar % 'Z' + 'A';
                }

                else if (Character.isLowerCase(c) && shiftedChar > 'z') {
                    shiftedChar = shiftedChar % 'z' + 'a';
                }
                encrypted.append((char) shiftedChar);
            } else if (Character.isDigit(c)) {

                int shiftedDigit = (c - '0' + key) % 10;
                encrypted.append((char) ('0' + shiftedDigit));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                int shiftedChar = c - key;
                if (Character.isUpperCase(c) && shiftedChar < 'A') {
                    shiftedChar = shiftedChar % 'A' + 'Z';
                } else if (Character.isLowerCase(c) && shiftedChar < 'a') {
                    shiftedChar = shiftedChar % 'a' + 'z';
                }
                decrypted.append((char) shiftedChar);
            } else if (Character.isDigit(c)) {

                int shiftedDigit = (c - '0' - key + 10) % 10;
                decrypted.append((char) ('0' + shiftedDigit));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}
