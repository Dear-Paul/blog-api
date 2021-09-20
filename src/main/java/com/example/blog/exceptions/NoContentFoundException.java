package com.example.blog.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoContentFoundException extends RuntimeException{
    private String message;

    public NoContentFoundException(String message) {
        this.message = message;
    }
}
