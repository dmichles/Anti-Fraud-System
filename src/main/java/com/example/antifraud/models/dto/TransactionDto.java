package com.example.antifraud.models.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionDto {
    private String result;
    private String info;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
