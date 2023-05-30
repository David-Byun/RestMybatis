package com.alpaco.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    private long id;
    @NonNull
    private String facilityName;
    @NonNull
    private String facilityImageUrl;
    private List<RoomFacility> roomFacilities;

}
