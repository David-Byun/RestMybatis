package com.alpaco.dev.entity;

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
public class Reservation {
    private long id;
    @NonNull
    private String reservationCode;
    @NonNull
    private int totalPrice;
    @NonNull
    private int totalGuest;
    @NonNull
    private LocalDateTime startDate;
    @NonNull
    private LocalDateTime endDate;
    @NonNull
    private Status status;
    @NonNull
    private boolean isReviewCreated;
    @NonNull
    private Room room;
    @NonNull
    private User user;
    @NonNull
    private Review review;
}
