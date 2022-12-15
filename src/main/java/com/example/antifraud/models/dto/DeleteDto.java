package com.example.antifraud.models.dto;

import com.example.antifraud.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDto {
    private String username;
    private String status;

    public static DeleteDto of(User user) {
        return new DeleteDto(user.getUsername(),"Deleted successfully!");
    }

}
