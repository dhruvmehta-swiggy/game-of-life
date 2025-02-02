package com.swiggy.game_of_life.Exceptions;

public class NullCellException extends RuntimeException {
    public NullCellException(String message) {
        super(message);
    }
}