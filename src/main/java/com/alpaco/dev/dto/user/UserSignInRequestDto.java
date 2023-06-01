package com.alpaco.dev.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSignInRequestDto {

    @NotBlank(message = "이메일은 필수 입력사항 입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
    private String password;
}