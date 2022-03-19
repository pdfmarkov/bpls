package com.markovandkrivonosov.blps.controllers;

import com.markovandkrivonosov.blps.exceptions.ResourceIsNotValidException;
import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.*;
import com.markovandkrivonosov.blps.module.requested.GrantRequestDto;
import com.markovandkrivonosov.blps.module.responses.AdminAdvertisementResponseDto;
import com.markovandkrivonosov.blps.module.responses.AdvertisementResponseDto;
import com.markovandkrivonosov.blps.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "${cors.urls}")
@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

    @Autowired
    UserService userService;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelService modelService;

    @Autowired
    RegionService regionService;

    @Autowired
    CityService cityService;

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping(value = "/ad/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdvertisementById(@RequestParam Long id) {

        Advertisement advertisement = advertisementService.findAdvertisementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Ad Not Found"));

        AdminAdvertisementResponseDto adminAdvertisementResponseDto = new AdminAdvertisementResponseDto();
        adminAdvertisementResponseDto.setId(advertisement.getId());
        adminAdvertisementResponseDto.setAcceptable(advertisement.getAcceptable());
        adminAdvertisementResponseDto.setVin(advertisement.getVin());
        adminAdvertisementResponseDto.setSts(advertisement.getSts());
        adminAdvertisementResponseDto.setStateNumber(advertisement.getStateNumber());
        adminAdvertisementResponseDto.setBrandName(advertisement.getBrand().getName());
        adminAdvertisementResponseDto.setModelName(advertisement.getModel().getName());
        adminAdvertisementResponseDto.setMileage(advertisement.getMileage());
        adminAdvertisementResponseDto.setIsRight(advertisement.getIsRight());
        adminAdvertisementResponseDto.setBody(advertisement.getBody());
        adminAdvertisementResponseDto.setReleaseYear(advertisement.getReleaseYear());
        adminAdvertisementResponseDto.setDriveUnit(advertisement.getDriveUnit());
        adminAdvertisementResponseDto.setFuel(advertisement.getFuel());
        adminAdvertisementResponseDto.setIsAuto(advertisement.getIsAuto());
        adminAdvertisementResponseDto.setColor(advertisement.getColor());
        adminAdvertisementResponseDto.setHasDocuments(advertisement.getHasDocuments());
        adminAdvertisementResponseDto.setNeedsRepair(advertisement.getNeedsRepair());
        adminAdvertisementResponseDto.setDescription(advertisement.getDescription());
        adminAdvertisementResponseDto.setPrice(advertisement.getPrice());
        adminAdvertisementResponseDto.setExchangePossible(advertisement.getExchangePossible());
        adminAdvertisementResponseDto.setStatus(advertisement.getStatus());
        adminAdvertisementResponseDto.setRegionName(advertisement.getRegion().getName());
        adminAdvertisementResponseDto.setCityName(advertisement.getCity().getName());
        adminAdvertisementResponseDto.setPhone(advertisement.getPhone());
        adminAdvertisementResponseDto.setAllowQuestions(advertisement.getAllowQuestions());
        adminAdvertisementResponseDto.setIsPaid(advertisement.getIsPaid());
        adminAdvertisementResponseDto.setImage(advertisement.getImage());

        return ResponseEntity.ok(adminAdvertisementResponseDto);
    }

    @PostMapping(value = "/ad/grant")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdvertisementById(@Valid @RequestBody GrantRequestDto grantRequestDto) {

        advertisementService.grantAdvertisementWithEmail(grantRequestDto);

        return ResponseEntity.ok("Ad status changed successfully!");
    }


}
