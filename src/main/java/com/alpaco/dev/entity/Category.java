package com.alpaco.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private long id;
    @NonNull
    private String categoryName;
    @NonNull
    private String categoryImage;
    private List<RoomCategory> roomCategory;

}
