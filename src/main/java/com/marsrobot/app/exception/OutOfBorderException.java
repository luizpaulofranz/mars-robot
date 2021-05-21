package com.marsrobot.app.exception;

public class OutOfBorderException extends RuntimeException {
	public OutOfBorderException() {
		super("O Robo saiu da área maxima permitida.");
	}
}
