package com.alpaco.dev.service;

import com.alpaco.dev.dto.user.UserSignInResponseDto;
import com.alpaco.dev.dto.user.UserSignUpRequestDto;
import com.alpaco.dev.entity.user.oauth.OauthProvider;
import com.alpaco.dev.mapper.UserMapper;
import com.alpaco.dev.util.jwt.JwtProvider;
import com.alpaco.dev.util.jwt.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoUserService {

    private static final String kakaoClientId = "83870487fd008580bcea50427296f384";
    private static final String redirectUri = "http://localhost:8888/app/user/kakao/callback";

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserSignInResponseDto kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {

        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        UserSignUpRequestDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        String userEmail = kakaoUserInfo.getEmail();

        // 3. 카카오ID로 회원가입 처리
        UserSignUpRequestDto kakaoUser = registerKakaoUserIfNeed(kakaoUserInfo);
        kakaoUser.setOauthProvider(OauthProvider.KAKAO);

        // 4. 토큰 발급
        Token token = jwtProvider.createToken(userEmail);

        return UserSignInResponseDto.builder()
                .email(userEmail)
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    // 1. "인가 코드"로 "액세스 토큰" 요청
    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoClientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    // 2. 토큰으로 카카오 API 호출(6, 7번 과정)
    private UserSignUpRequestDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄(필요한 정보들만 추출하는 로직)
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();

        UserSignUpRequestDto dto = new UserSignUpRequestDto();
        dto.setUsername("kakao_" + id);
        dto.setNickname(nickname);
        dto.setBirth("");
        dto.setEmail(email);
        dto.setOauthProvider(OauthProvider.KAKAO);

        return dto;
    }

    // 3. 카카오ID로 회원가입 처리
    private UserSignUpRequestDto registerKakaoUserIfNeed(UserSignUpRequestDto kakaoUserInfo) {
        // DB 에 중복된 email이 있는지 확인
        String kakaoEmail = kakaoUserInfo.getEmail();
        String nickname = kakaoUserInfo.getNickname();
        UserSignUpRequestDto kakaoUser = userMapper.findUserByEmail(kakaoEmail).orElse(null);

        if (kakaoUser == null) {
            // 회원가입
            // password: random UUID
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);
            String username = "kakao_" + nickname;

            kakaoUser = new UserSignUpRequestDto();
            kakaoUser.setUsername(username);
            kakaoUser.setPassword(encodedPassword);
            kakaoUser.setNickname(nickname);
            kakaoUser.setBirth("");
            kakaoUser.setEmail(kakaoEmail);
            kakaoUser.setOauthProvider(OauthProvider.KAKAO);
            userMapper.saveUser(kakaoUser.toEntity());
        }
        return kakaoUser;
    }
}
