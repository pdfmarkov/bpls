package com.markovandkrivonosov.blps.controllers;


import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.User;
import com.markovandkrivonosov.blps.module.requested.AdvertisementRequestDto;
import com.markovandkrivonosov.blps.module.responses.UserResponseDto;
import com.markovandkrivonosov.blps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "${cors.urls}")
@RestController
@RequestMapping("/api/ad")
public class AdvertisementController {

    @Autowired
    UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllAdvertisements() {

        //TODO: Добавить логику + фильтр

        return ResponseEntity.ok("Here's your list of ads");
    }


    @PutMapping("/add")
    public ResponseEntity<?> addNewAdvertisement(@Valid @RequestBody AdvertisementRequestDto advertisementRequestDto) {

        //TODO: Добавить логику

        return ResponseEntity.ok("Ad successfully added!");
    }

    private String getCurrentUsername() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
