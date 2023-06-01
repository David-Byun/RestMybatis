package com.alpaco.dev.exception;

import lombok.Getter;

@Getter
public class AwsException extends RuntimeException{

    private final BaseResponseStatus status;

    public AwsException(BaseResponseStatus status) {
        this.status = status;
    }
}
