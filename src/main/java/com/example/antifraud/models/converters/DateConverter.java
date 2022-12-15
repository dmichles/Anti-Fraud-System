package com.example.antifraud.models.converters;

import javax.persistence.AttributeConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter implements AttributeConverter<LocalDateTime,String> {
    @Override
    public String convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss"));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss"));
    }
}
