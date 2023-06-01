package com.alpaco.dev.dto.room;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RoomDetailResponseDto {
    private long roomId;
    private String hostName;
    private String metropolitan;
    private String city;
    private String town;
    private Float latitude; // 위도
    private Float longitude; // 경도
    private String roomName;
    private int price;
    private int maxGuest;
    private int maxPet;
    private String roomDecription;
    private String checkinTime;
    private String checkoutTime;
    private double roomAverageScore; // Review AVG
    private long reviewCount; // Review COUNT
    private List<String> roomImageUrls;

//    private List<RoomDetailReview> reviewPreviews;
//    private List<RoomFacilityInfo> facilities;

}
