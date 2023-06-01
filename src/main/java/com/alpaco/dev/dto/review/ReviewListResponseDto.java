package com.alpaco.dev.dto.review;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReviewListResponseDto {
    private long reviewId;
    private long reservationId;
    private int score;
    private String content;
    private String updatedAt;
    private List<String> reviewImageUrls;
}
