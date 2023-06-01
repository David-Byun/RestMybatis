package com.alpaco.dev.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "responseCode", "responseMessage", "result"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final int responseCode;
    private final String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final T result;

    // Request Success, Request 성공시 무조건 result 존재
    // 1번
    public BaseResponse(T result){
        this.isSuccess = BaseResponseStatus.SUCCESS.isSuccess();
        this.responseMessage = BaseResponseStatus.SUCCESS.getResponseMessage();
        this.responseCode = BaseResponseStatus.SUCCESS.getResponseCode();
        this.result = result;
    }

    // Common Exception, 보내는 데이터 없이 메시지만 출력하면 되는 에러 처리
    // 2번
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess(); // result는 항상 fail
        this.responseMessage = status.getResponseMessage();
        this.responseCode = status.getResponseCode();
        this.result = null;
    }

    // Common Exception, 보내는 데이터 포함하는 메시지 출력하면 되는 에러 처리
    // 3번
    public BaseResponse(BaseResponseStatus status, T result) {
        this.isSuccess = status.isSuccess();
        this.responseMessage = status.getResponseMessage();
        this.responseCode = status.getResponseCode();
        this.result = result;
    }
}
