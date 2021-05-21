package com.marsrobot.app.exception;

public class InvalidCommandException extends RuntimeException {
	public InvalidCommandException() {
		super("O comando recebido é inválido.");
	}
}
