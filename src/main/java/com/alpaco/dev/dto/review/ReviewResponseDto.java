package com.alpaco.dev.dto.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseDto {
    private long reviewId;
    private long reservationId;
    private int score;
    private String content;
    private LocalDateTime LocalDateTime;
}
