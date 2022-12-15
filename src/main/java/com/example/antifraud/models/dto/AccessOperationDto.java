package com.example.antifraud.models.dto;

import com.example.antifraud.models.constants.AccessOperation;

public class AccessOperationDto {
    private String username;
    private AccessOperation operation;

    public String getUsername() {
        return username;
    }

    public AccessOperation getOperation() {
        return operation;
    }
}
