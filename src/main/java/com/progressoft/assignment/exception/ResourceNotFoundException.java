package com.progressoft.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String id;

    public ResourceNotFoundException(String id) {
        super(String.format("Deal with ID: %s not found", id));
        this.id = id;
    }
}
