package com.projects.e_commerce_service.services;

import com.projects.e_commerce_service.entities.User;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.security.*;
import io.jsonwebtoken.Jwts;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.List;

@Component
@Setter
@Getter
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final String SECRET_KEY = "your_secret_key";
    private final long EXPIRATION_TIME = 86400000; // 24 hours

//    @Value("${custom.key-pair.private_key}")
    File privateKeyFile= new File("C:/Users/suzan/Downloads/private_key.pem");

    public String generateToken(User user) throws NoSuchAlgorithmException {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        RSAPrivateKey keyPair = getRSAPrivateKey();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("scope", List.of(user.getRole().toString()))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(keyPair, SignatureAlgorithm.RS256)
                .compact();
    }

    public RSAPrivateKey getRSAPrivateKey() {
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try (FileReader keyReader = new FileReader(privateKeyFile);
             PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

