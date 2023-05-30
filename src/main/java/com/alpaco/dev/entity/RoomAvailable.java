package com.alpaco.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomAvailable {
    private long id;
    @NonNull
    private LocalDate availableDay;
    @NonNull
    private boolean isAvailable;
    @NonNull
    private Room room;
}
