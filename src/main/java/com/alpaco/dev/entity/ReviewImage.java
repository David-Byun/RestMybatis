package com.alpaco.dev.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewImage {
    private long id;
    @NonNull
    private String reviewImageUrl;
    @NonNull
    private Review review;

    @Builder
    public ReviewImage(@NonNull String reviewImageUrl, @NonNull Review review) {
        this.reviewImageUrl = reviewImageUrl;
        this.review = review;
    }
}
