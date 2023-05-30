package com.alpaco.dev.entity;

import com.alpaco.dev.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    private long id;
    private boolean isChecked;
    private String field;
    @NonNull
    private Room room;
    @NonNull
    private User user;
}
