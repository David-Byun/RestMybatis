package com.alpaco.dev.mapper;

import com.alpaco.dev.dto.user.UserSignUpRequestDto;
import com.alpaco.dev.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void saveUser(User user); // Dto -> entity 변환완료
    int emailCheck(String email);

    Optional<UserSignUpRequestDto> findUserByEmail(String email);

    Long findUserIdByEmail(String email);
}
