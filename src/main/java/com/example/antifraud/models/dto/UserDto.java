package com.example.antifraud.models.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
