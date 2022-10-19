package com.kerimfettahoglu.usermanagement.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String businessExceptionHandler(MethodArgumentNotValidException ex) {
		log.error(ex.getMessage(), ex);
		return "Validasyon hatası.\n" + ex.getMessage() ;
	}
	
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String businessExceptionHandler(RuntimeException ex) {
		log.error(ex.getMessage(), ex);
		return ex.getMessage() ;
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String exceptionHandler(Exception ex) {
		log.error("Something bad happened.", ex);
		return "Something bad happened. " + ex.getMessage();
	}
}