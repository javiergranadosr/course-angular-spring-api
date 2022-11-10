package com.jgr.api.clients.utils;

import lombok.Data;

@Data
public class ClientResponseSuccess<T> {
    private String message;
    private T data;
}
