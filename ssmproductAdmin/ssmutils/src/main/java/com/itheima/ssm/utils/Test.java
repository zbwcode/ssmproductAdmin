package com.itheima.ssm.utils;

public class Test {
    public static void main(String[] args) {
        String s = PasswordEncoderUtils.passwordEncoder("123");
        System.out.println(s);
    }
}
