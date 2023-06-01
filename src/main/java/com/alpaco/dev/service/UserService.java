package com.alpaco.dev.service;

import com.alpaco.dev.dto.user.UserSignInRequestDto;
import com.alpaco.dev.dto.user.UserSignInResponseDto;
import com.alpaco.dev.dto.user.UserSignUpRequestDto;
import com.alpaco.dev.dto.user.UserSignUpResponseDto;
import com.alpaco.dev.entity.user.User;
import com.alpaco.dev.exception.BaseResponseStatus;
import com.alpaco.dev.exception.UserException;
import com.alpaco.dev.mapper.UserMapper;
import com.alpaco.dev.util.jwt.JwtProvider;
import com.alpaco.dev.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.alpaco.dev.exception.BaseResponseStatus.*;
import static com.alpaco.dev.exception.BaseResponseStatus.INVALID_EMAIL_OR_PASSWORD;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserSignUpResponseDto joinUser(UserSignUpRequestDto userRequest) {

        validateDuplicateEmail(userRequest.getEmail()); // 중복확인, 캡슐화

        User user = userRequest.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userMapper.saveUser(user);

        return UserSignUpResponseDto.builder()
                .id(user.getUser_id())
                .build();
    }

    private boolean validateDuplicateEmail(String email) {
        if (userMapper.emailCheck(email) > 0) {
            throw new UserException(DUPLICATED_EMAIL);
        }
        return true;
    }

    public UserSignInResponseDto signin(UserSignInRequestDto userRequest) {
        User user = userMapper.findUserByEmail(userRequest.getEmail())
                .orElseThrow(() -> new UserException(NONE_EXIST_USER)).toEntity();

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new UserException(INVALID_EMAIL_OR_PASSWORD);
        }

        String email = user.getEmail();
        Token token = jwtProvider.createToken(email);

        return UserSignInResponseDto.builder()
                .email(email)
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}