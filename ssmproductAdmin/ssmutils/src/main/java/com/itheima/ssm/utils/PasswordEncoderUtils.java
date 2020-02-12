package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    public static String passwordEncoder(String password){
        return bCryptPasswordEncoder.encode(password);
    }


}
