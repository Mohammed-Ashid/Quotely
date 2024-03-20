package com.example.quotely.demo.Vo;

import java.util.UUID;

public class AuthKeyGenerator {
    public static String generateAuthKey() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}