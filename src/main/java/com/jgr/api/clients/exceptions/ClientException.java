package com.jgr.api.clients.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientException  extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public ClientException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
