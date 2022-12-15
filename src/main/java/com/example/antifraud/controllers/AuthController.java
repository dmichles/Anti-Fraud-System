package com.example.antifraud.controllers;

import com.example.antifraud.models.dto.AccessOperationDto;
import com.example.antifraud.models.dto.UserAuthorityDto;
import com.example.antifraud.models.dto.UserDto;
import com.example.antifraud.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthController {

    public final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/user")
    public ResponseEntity<?> signup(@RequestBody @Valid UserDto userDto) {
        ResponseEntity<?> response = userService.addUser(userDto);
        return response;
    }

    @GetMapping("/api/auth/list")
    public ResponseEntity<?> list(){
        ResponseEntity<?> response = userService.list();
        return response;
    }

    @DeleteMapping("/api/auth/user/{username}")
    public ResponseEntity<?> delete(@PathVariable String username) {
        ResponseEntity<?> response = userService.delete(username);
        return response;
    }

    @PutMapping("/api/auth/role")
    public ResponseEntity<?> changeAuthority(@RequestBody UserAuthorityDto userAuthorityDto) {
        ResponseEntity<?> response = userService.changeAuthority(userAuthorityDto);
        return response;
    }

    @PutMapping("/api/auth/access")
    public ResponseEntity<?> changeAccess(@RequestBody AccessOperationDto accessOperationDto) {
        ResponseEntity<?> response = userService.access(accessOperationDto);
        return response;
    }
}

