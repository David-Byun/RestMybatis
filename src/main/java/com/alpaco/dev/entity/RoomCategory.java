package com.alpaco.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCategory {
    private long id;
    @NonNull
    private Room room;
    @NonNull
    private Category category;
}
