package com.example.antifraud.models.dto;

import com.example.antifraud.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfoDto {
    private Long id;
    private String name;
    private String username;
    private String role;

    public static UserInfoDto of(User user) {
        return new UserInfoDto(user.getId(),user.getName(),user.getUsername(),user.getAuthority().getName());
    }
}
