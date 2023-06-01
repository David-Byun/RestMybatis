package com.alpaco.dev.exception;

import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException{

    private final BaseResponseStatus status;

    public ReviewException(BaseResponseStatus status) {
        this.status = status;
    }
}
