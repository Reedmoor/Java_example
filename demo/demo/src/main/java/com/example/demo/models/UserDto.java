package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.val;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    String name;
    String email;
    String password;

    UserDto(){}
}
