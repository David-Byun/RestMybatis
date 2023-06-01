package com.alpaco.dev.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final BaseResponseStatus status;

    public UserException(BaseResponseStatus status) {
        this.status = status;
    }
}