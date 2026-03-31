package com.example.domain.service;

public class TitleAlreadyUsedException extends RuntimeException {
    public TitleAlreadyUsedException(String message) {
        super(message);
    }
}
