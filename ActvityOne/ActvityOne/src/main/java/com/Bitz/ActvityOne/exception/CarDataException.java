package com.Bitz.ActvityOne.exception;

public class CarDataException extends RuntimeException {
    public CarDataException(String message) {
        super(message);
    }
    
    public CarDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

