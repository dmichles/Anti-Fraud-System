package com.example.antifraud.models.dto;

import com.example.antifraud.models.constants.UserAuthority;

public class UserAuthorityDto {
    private String username;
    private UserAuthority role;

    public String getUsername() {
        return username;
    }

    public UserAuthority getRole() {
        return role;
    }
}
