package com.accessmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class VerificationResult {
    private String id;
    private String[] errors;
    private boolean valid;
    private String message;

    public VerificationResult(String id) {
        this.id = id;
        this.errors = new String[]{};
        this.valid = true;
    }

    public VerificationResult(String[] errors) {
        this.errors = errors;
        this.id = "";
        this.valid = false;
    }
}
