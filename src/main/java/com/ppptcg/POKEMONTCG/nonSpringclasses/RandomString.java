package com.ppptcg.POKEMONTCG.nonSpringclasses;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private final int length;

    public RandomString(int length) {
        this.length = length;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
