package com.alpaco.dev.entity;

import com.alpaco.dev.entity.user.Status;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseTimeEntity {
    private long review_id;
    @NonNull
    private int score;
    private String content;
    @NonNull
    private Status status;
    @NonNull
    private Reservation reservation;
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @Builder
    public Review(int score, String content, Status status, Reservation reservation) {
        this.score = score;
        this.content = content;
        this.status = status;
        this.reservation = reservation;
    }

}
