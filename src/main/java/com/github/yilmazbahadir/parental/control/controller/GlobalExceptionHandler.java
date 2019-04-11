package com.github.yilmazbahadir.parental.control.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.yilmazbahadir.parental.control.exception.ControlLevelNotFoundException;
import com.github.yilmazbahadir.parental.control.exception.TechnicalFailureException;
import com.github.yilmazbahadir.parental.control.exception.TitleNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ TitleNotFoundException.class })
	public ResponseEntity<Error> handleTitleNotFoundException(Exception e) {
		logger.error("TitleNotFoundException exception has occured");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Error("404", e.getClass().getSimpleName(), e.getMessage()));
	}

	@ExceptionHandler({ TechnicalFailureException.class })
	public ResponseEntity<Error> handleTechnicalFailureException(Exception e) {
		logger.error("TechnicalFailureException exception has occured");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new Error("500", e.getClass().getSimpleName(), e.getMessage()));
	}

	@ExceptionHandler({ ControlLevelNotFoundException.class })
	public ResponseEntity<Error> handleControlLevelNotFoundException(Exception e) {
		logger.error("ControlLevelNotFoundException exception has occured");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new Error("404", e.getClass().getSimpleName(), e.getMessage()));
	}

	public static class Error {
		private final String code;
		private final String type;
		private final String message;

		public Error(String code, String type, String message) {
			this.code = code;
			this.type = type;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getType() {
			return type;
		}

		public String getMessage() {
			return message;
		}

	}
}
