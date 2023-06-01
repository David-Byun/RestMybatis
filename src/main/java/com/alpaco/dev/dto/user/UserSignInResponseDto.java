package com.alpaco.dev.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignInResponseDto {
    private String email;
    private String accessToken;
    private String refreshToken;
}
