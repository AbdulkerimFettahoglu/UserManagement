package com.kerimfettahoglu.usermanagement.service.exception;

public class AppUserNotFoundException extends RuntimeException {
	public AppUserNotFoundException(Long id) {
		super("There is no user with " + id + " id number.");
	}
}
