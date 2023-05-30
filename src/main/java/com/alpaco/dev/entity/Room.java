package com.alpaco.dev.entity;

import com.alpaco.dev.entity.BaseTimeEntity;
import com.alpaco.dev.entity.user.Status;
import com.alpaco.dev.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseTimeEntity {
    private long id;
    @NonNull
    private String roomName;
    @NonNull
    private String region;   // 서울특별시 인천광역시 부산광역시.. 경상도 경기도 전라도 제주도
    @NonNull
    private String city;     // 서울시 인천시 부산시 안산시 제주시 서귀포시 ~군. 저장시에는 행정구분단위 제외하고 이름만
    @NonNull
    private String district; // 구/읍/면 (리는 제외). 저장시에는 행정구분 포함
    @NonNull
    private Float latitude;
    @NonNull
    private Float longitude;
    @NonNull
    private int price;
    private String main_image;
    @NonNull
    private int max_guest;
    private int max_pet;
    private String description;
    @NonNull
    private LocalDateTime checkInTime;
    @NonNull
    private LocalDateTime checkOutTime;
    @NonNull
    private Status status;
    @NonNull
    private User user;
}
