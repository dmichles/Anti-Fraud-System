package com.example.antifraud.models.constants;

public enum UserAuthority {
    ADMINISTRATOR, MERCHANT, SUPPORT;

    public boolean isAdministrator() {
        return this == ADMINISTRATOR;
    }
}
