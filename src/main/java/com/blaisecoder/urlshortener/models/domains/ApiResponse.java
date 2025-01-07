package com.blaisecoder.urlshortener.models.domains;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ApiResponse <T>{
    private final String timestamp= LocalDateTime.now().toString();
    private T data;
    private String message;
    private HttpStatus status;

    public ApiResponse(T data, String message, HttpStatus status){
this.data=data;
this.message=message;
this.status=status;

    }
    public ResponseEntity<ApiResponse<T>> toResponseEntity(){
        assert this.status!=null;
        return ResponseEntity.status(this.status).body(this);
    }
}
