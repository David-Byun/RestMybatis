package com.alpaco.dev.util.exception;

import com.alpaco.dev.exception.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtInvalidException extends RuntimeException{

    private final BaseResponseStatus status;

    public JwtInvalidException(BaseResponseStatus status) {
        this.status = status;
    }
}