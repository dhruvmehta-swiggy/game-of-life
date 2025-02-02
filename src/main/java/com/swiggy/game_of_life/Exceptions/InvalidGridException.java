package com.swiggy.game_of_life.Exceptions;

public class InvalidGridException extends RuntimeException {
    public InvalidGridException(String message) {
        super(message);
    }
}