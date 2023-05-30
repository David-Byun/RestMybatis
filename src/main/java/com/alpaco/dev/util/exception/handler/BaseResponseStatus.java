package com.alpaco.dev.util.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : Request 오류
     */
    // 입력값 예외
    INVALID_REQUEST(false, 2000, "잘못된 요청이 존재합니다."),
    EMPTY_REQUEST_PARAMETER(false, 2098, "Request Parameter가 존재하지 않습니다."),

    /**
     * 3000 : Common 공통 오류
     */
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;
}
