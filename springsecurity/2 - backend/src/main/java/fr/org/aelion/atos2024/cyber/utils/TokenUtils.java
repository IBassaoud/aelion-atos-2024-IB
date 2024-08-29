package fr.org.aelion.atos2024.cyber.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.org.aelion.atos2024.cyber.models.entities.User;

import java.time.Instant;

public class TokenUtils {

    public static String generateJWT(User u) {
        return JWT.create()
                .withClaim("email", u.getEmail())
                .withClaim("datetime-claim", Instant.now())
                .sign(Algorithm.HMAC512("SECRET"));
    }

    public static DecodedJWT decodeJWT(String token) {
        return JWT.require(Algorithm.HMAC512("SECRET")).build().verify(token);
    }
}
