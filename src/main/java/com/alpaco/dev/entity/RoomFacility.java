package com.alpaco.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomFacility {
    private long id;
    @NonNull
    private Room room;
    @NonNull
    private Facility facility;
}
