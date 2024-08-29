package fr.org.aelion.atos2024.cyber.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class HashUtils {
    @Value("${spring.application.security.hash.algo}")
    private String algo;

    @Value("${spring.application.security.hash.salt}")
    private String salt;


    public String generate(String value){
        String hash;
        try {
            // TODO: put hashing logic in another class/method
            // hash + salt pwd
            MessageDigest md = MessageDigest.getInstance(algo);
            // TODO: Put the salt String in properties file
            md.update(salt.getBytes());
            byte[] digest = md.digest(value.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
