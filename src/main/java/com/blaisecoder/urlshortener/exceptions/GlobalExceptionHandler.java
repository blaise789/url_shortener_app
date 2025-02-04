package com.blaisecoder.urlshortener.exceptions;

import com.blaisecoder.urlshortener.models.domains.ApiResponse;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Locale;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebMvc
public class GlobalExceptionHandler {
    private final MessageSource messageSource;
    public GlobalExceptionHandler(MessageSource messageSource){
        this.messageSource=messageSource;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, Locale locale){
        String errorMessage=messageSource.getMessage(ex.getMessage(), ex.getArgs(),locale);
        return new ApiResponse<>(null,errorMessage,HttpStatus.NOT_FOUND).toResponseEntity();

    }
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> duplicatedRecordException(DuplicateRecordException ex, Locale locale){
        String errorMessage=messageSource.getMessage(ex.getMessage(),ex.getArgs(),locale);
        return new ApiResponse<>(null,errorMessage,HttpStatus.BAD_GATEWAY).toResponseEntity();


    }


}
