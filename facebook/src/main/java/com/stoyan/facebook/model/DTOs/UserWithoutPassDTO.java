package com.stoyan.facebook.model.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWithoutPassDTO {

    private int id;
    private String email;
    private int age;
}
