package com.progressoft.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceCreationFailedException extends RuntimeException {

    public ResourceCreationFailedException(String id) {
        super(String.format("Deal with ID: %s failed to create", id));
    }
}
