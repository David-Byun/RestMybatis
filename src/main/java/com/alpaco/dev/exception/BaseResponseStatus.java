package com.alpaco.dev.exception;

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
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    // UserException
    DUPLICATED_EMAIL(false, 2010, "중복된 이메일입니다."),
    NONE_EXIST_USER(false, 2011, "존재하지 않는 회원입니다."),
    INVALID_EMAIL_OR_PASSWORD(false, 2012, "이메일 혹은 비밀번호가 잘못되었습니다."),
    POST_IMAGE_INVALID_EXTENSION(false, 2032, "업로드가 불가능한 확장자입니다."),
    POST_IMAGE_CONVERT_ERROR(false, 2099, "AWS S3 이미지 변환이 실패했습니다."),
    POST_REVIEW_NONE_RESERVATION(false, 2041, "존재하지 않는 예약입니다."),
    POST_REVIEW_ALREADY_CREATED(false, 2042, "예약건에 대한 리뷰가 이미 존재합니다."),
    NONE_REVIEW(false, 2031, "존재하지 않는 리뷰입니다."),
    // JwtException
    UNSUPPORTED_TOKEN_TYPE(false, 2200, "지원되지 않는 토큰 타입입니다."),
    MALFORMED_TOKEN_TYPE(false, 2201, "인증 토큰이 올바르게 구성되지 않았습니다."),
    INVALID_SIGNATURE_JWT(false, 2202, "올바른 인증 시그니처가 아닙니다."),
    INVALID_TOKEN_TYPE(false, 2203, "잘못된 토큰 타입입니다."),
    ACCESS_DENIED(false, 2204, "권한이 없는 유저의 접근입니다."),
    EXPIRED_ACCESS_TOKEN(false, 2205, "이미 만료된 Access 토큰입니다."),
    EMPTY_AUTHORIZATION_HEADER(false, 2206, "Authorization 헤더가 없습니다.");

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;
}