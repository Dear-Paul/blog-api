package com.example.blog.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class BlogApiException extends RuntimeException{

    private  HttpStatus status;
    private  String message;

    public BlogApiException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BlogApiException(String message) {
        this.message = message;
    }

    public BlogApiException(HttpStatus status, String message, Throwable exception){
        super(exception);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public String getMessage(){
       return message;
    }
}
