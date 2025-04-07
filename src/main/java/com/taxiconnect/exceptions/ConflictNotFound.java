package com.taxiconnect.exceptions;

public class ConflictNotFound extends RuntimeException {
    public ConflictNotFound(String message) {
        super(message);
    }
}
