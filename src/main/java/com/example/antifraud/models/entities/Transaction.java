package com.example.antifraud.models.entities;

import com.example.antifraud.models.constants.Region;
import com.example.antifraud.models.converters.CustomJsonDateDeserializer;
import com.example.antifraud.models.converters.DateConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long amount;

    private String ip;

    private String number;

    private Region region;
    //@Convert(converter = DateConverter.class)

    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public LocalDateTime getDate() {
        return date;
    }
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
