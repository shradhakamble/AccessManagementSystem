package com.accessmanagementsystem.util;

import com.accessmanagementsystem.dto.JwtGenerators;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class JwtUtil {
    public IJwtGenerator getGenerator(SignatureAlgorithm signatureAlgorithm) {
        return JwtGenerators.getGenerator(signatureAlgorithm);
    }

    public IJwtGenerator getGenerator(String signatureAlgorithm) {
        return JwtGenerators.getGenerator(SignatureAlgorithm.forName(signatureAlgorithm));
    }

    public String getAlgorithm(String token) {
        String[] split = token.split("\\.");
        byte[] decode = Base64.getUrlDecoder().decode(split[0]);
        JSONObject object = new JSONObject(new String(decode, StandardCharsets.UTF_8));
        return String.valueOf(object.get("alg"));
    }

}

