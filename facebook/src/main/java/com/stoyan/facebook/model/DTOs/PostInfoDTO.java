package com.stoyan.facebook.model.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PostInfoDTO {
    private int id;
    private String title;
    private String description;
    private UserWithoutPassDTO owner;
}
