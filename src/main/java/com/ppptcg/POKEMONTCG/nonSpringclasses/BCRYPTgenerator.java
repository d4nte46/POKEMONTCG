package com.ppptcg.POKEMONTCG.nonSpringclasses;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BCRYPTgenerator {
    public String generatepassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        return hashedPassword;

    }

    public Boolean matchpassword(String p1, String p2){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(p1,p2);
    }
}
