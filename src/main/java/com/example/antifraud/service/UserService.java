package com.example.antifraud.service;

import com.example.antifraud.models.constants.AccessOperation;
import com.example.antifraud.models.constants.UserAuthority;
import com.example.antifraud.models.dto.*;
import com.example.antifraud.models.entities.Authority;
import com.example.antifraud.models.entities.User;
import com.example.antifraud.repository.AuthorityRepository;
import com.example.antifraud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<?> addUser(UserDto userDto) {
        var u = userRepository.findUserByUsername(userDto.getUsername());
        if(u.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        updateUserAuthority(user);
        userRepository.save(user);
        UserInfoDto uDto = UserInfoDto.of(user);
        return new ResponseEntity<>(uDto, HttpStatus.CREATED);
    }

    private void updateUserAuthority(User user){
        if(userRepository.count() == 0){
            Authority authority = authorityRepository.findAuthorityByName("ADMINISTRATOR");
            user.addAuthority(authority);
            user.setAccountNonLocked(true);
        } else {
            Authority authority = authorityRepository.findAuthorityByName("MERCHANT");
            user.addAuthority(authority);
            user.setAccountNonLocked(false);
        }
    }

    public ResponseEntity<?> list() {
        List<User> list = userRepository.findAllByOrderByIdAsc();
        List<UserInfoDto> l = list.stream().map(UserInfoDto::of).toList();
        return ResponseEntity.ok(l);
    }

    public ResponseEntity<?> delete(String username) {
        var u = userRepository.findUserByUsername(username);
        if (!u.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(u.get());
        DeleteDto deleteDto = DeleteDto.of(u.get());
        return ResponseEntity.ok(deleteDto);
    }

    public ResponseEntity<?> changeAuthority(UserAuthorityDto userAuthorityDto) {
        var u = userRepository.findUserByUsername(userAuthorityDto.getUsername());
        if (!u.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User user = u.get();
        if (!userAuthorityDto.getRole().equals(UserAuthority.MERCHANT)
                && !userAuthorityDto.getRole().equals(UserAuthority.SUPPORT)) {
            System.out.println(userAuthorityDto.getRole());
            return ResponseEntity.badRequest().build();
        }
        Authority authority = authorityRepository.findAuthorityByName(userAuthorityDto.getRole().toString());
        user.setAuthority(authority);
        userRepository.save(user);
        UserInfoDto userInfoDto = UserInfoDto.of(user);
        return ResponseEntity.ok(userInfoDto);
    }

    public ResponseEntity<?> access(AccessOperationDto accessOperationDto) {
        Authority authority = authorityRepository.findAuthorityByName(accessOperationDto.getUsername());
        if (authority.getName().equals(UserAuthority.ADMINISTRATOR)) {
            return ResponseEntity.badRequest().build();
        }
        var u = userRepository.findUserByUsername(accessOperationDto.getUsername());
        if (!u.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = u.get();

        switch(accessOperationDto.getOperation()) {
            case LOCK -> {
                user.setAccountNonLocked(false);
                userRepository.save(user);
            }
            case UNLOCK -> {
                user.setAccountNonLocked(true);
                userRepository.save(user);
            }
        }
        String status = accessOperationDto.getOperation() == AccessOperation.LOCK?"locked":"unlocked";
        return ResponseEntity.ok(Map.of("status","user "+accessOperationDto.getUsername() + " " + status));
    }
}
