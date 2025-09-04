package com.Bitz.ActvityOne.exception;

import java.util.List;

public class CarValidationException extends RuntimeException {
    private final List<String> validationErrors;
    
    public CarValidationException(String message) {
        super(message);
        this.validationErrors = List.of(message);
    }
    
    public CarValidationException(List<String> validationErrors) {
        super("Validation failed: " + String.join(", ", validationErrors));
        this.validationErrors = validationErrors;
    }
    
    public CarValidationException(String message, Throwable cause) {
        super(message, cause);
        this.validationErrors = List.of(message);
    }
    
    public List<String> getValidationErrors() {
        return validationErrors;
    }
}

