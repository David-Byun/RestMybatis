package com.alpaco.dev.dto.room;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoomListResponseDto {
    private long roomId;
    private String city;
    private String district;
    private int price;
    private String roomMainImage;
    private double averageReviewScore;
    private int reviewCount;
}
