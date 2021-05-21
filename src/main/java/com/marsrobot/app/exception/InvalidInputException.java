package com.marsrobot.app.exception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message) { super(message); }

    public InvalidInputException() { super("O arquivo de entrada não está em formato correto."); }
}
