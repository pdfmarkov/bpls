package com.markovandkrivonosov.blps.controllers;


import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.User;
import com.markovandkrivonosov.blps.module.responses.UserResponseDto;
import com.markovandkrivonosov.blps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "${cors.urls}")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        User user = userService.findUserByPhone(getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Error: User Not Found"));
        UserResponseDto userResponseDto = getUserResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    private String getCurrentUsername() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    private UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setDescription(user.getDescription());
        userResponseDto.setUsername(user.getPhone());
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        return userResponseDto;
    }
}
