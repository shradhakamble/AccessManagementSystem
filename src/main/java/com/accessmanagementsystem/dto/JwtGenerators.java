package com.accessmanagementsystem.dto;

import com.accessmanagementsystem.util.HS256JwtGenerator;
import com.accessmanagementsystem.util.IJwtGenerator;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum JwtGenerators {
    HS256(SignatureAlgorithm.HS256, new HS256JwtGenerator()),
    ;

    private static final Map<SignatureAlgorithm,IJwtGenerator> sigToGeneratorMap = new HashMap<>();
    private final IJwtGenerator generator;
    private final SignatureAlgorithm signatureAlgorithm;

    static {
        for (JwtGenerators value : JwtGenerators.values()) {
            sigToGeneratorMap.put(value.getSignatureAlgorithm(), value.getGenerator());
        }
    }

    JwtGenerators(SignatureAlgorithm signatureAlgorithm, IJwtGenerator jwtGenerator) {
        this.signatureAlgorithm = signatureAlgorithm;
        this.generator = jwtGenerator;
    }

    public static IJwtGenerator getGenerator(SignatureAlgorithm signatureAlgorithm) {
        return sigToGeneratorMap.get(signatureAlgorithm);
    }
}
