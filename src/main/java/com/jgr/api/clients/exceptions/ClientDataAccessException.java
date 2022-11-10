package com.jgr.api.clients.exceptions;

import org.springframework.dao.DataAccessException;

public class ClientDataAccessException extends DataAccessException {
    public ClientDataAccessException(String msg) {
        super(msg);
    }
}
