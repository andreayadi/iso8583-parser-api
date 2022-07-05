package com.personal.utility.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.personal.utility.exception.custom.CustomNullException;

@ControllerAdvice
public class CustomExceptionHandler {
    private ErrorMessage<Object> errorMessage;

    @ExceptionHandler(value = CustomNullException.class)
    public ResponseEntity<ErrorMessage<Object>> handlerNullException(Exception exception){
        errorMessage = new ErrorMessage<>(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }
}
