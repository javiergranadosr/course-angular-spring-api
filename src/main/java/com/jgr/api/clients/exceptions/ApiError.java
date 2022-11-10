package com.jgr.api.clients.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@Data
public class ApiError {
    private Instant timestamp;
    private int code;
    private String messageCode;
    private String message;
    private String path;
}


