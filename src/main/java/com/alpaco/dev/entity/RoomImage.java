package com.alpaco.dev.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomImage {
    private long id;
    @NonNull
    private String roomImageUrl;
    @NonNull
    private Room room;

}
