package com.markovandkrivonosov.blps.controllers;


import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.*;
import com.markovandkrivonosov.blps.module.requested.AdvertisementRequestDto;
import com.markovandkrivonosov.blps.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "${cors.urls}")
@RestController
@RequestMapping("/api/ad")
public class AdvertisementController {

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

    @GetMapping(value = "/get")
    public ResponseEntity<?> searchAllAdvertisements(@RequestParam Map<String,String> values) {
        return ResponseEntity.ok(advertisementService.findAllAdvertisementsBySpecification(values));
    }

    @PutMapping("/add")
    public ResponseEntity<?> addNewAdvertisement(@Valid @RequestBody AdvertisementRequestDto advertisementRequestDto) {

        Advertisement advertisement = new Advertisement();

        advertisement.setVin(advertisementRequestDto.getVin());
        if (advertisementRequestDto.getSts() != null) advertisement.setSts(advertisementRequestDto.getSts());
        if (advertisementRequestDto.getStateNumber() != null) advertisement.setStateNumber(advertisementRequestDto.getStateNumber());
        advertisement.setBrand(brandService.findBrandByName(advertisementRequestDto.getBrandName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Brand Not Found")));
        advertisement.setModel(modelService.findModelByName(advertisementRequestDto.getModelName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Model Not Found")));
        advertisement.setMileage(advertisementRequestDto.getMileage());
        advertisement.setIsRight(advertisementRequestDto.getIsRight());
        advertisement.setBody(advertisementRequestDto.getBody());
        advertisement.setReleaseYear(advertisementRequestDto.getReleaseYear());
        if (advertisementRequestDto.getDriveUnit() != null) advertisement.setDriveUnit(advertisementRequestDto.getDriveUnit());
        if (advertisementRequestDto.getFuel() != null) advertisement.setFuel(advertisementRequestDto.getFuel());
        if (advertisementRequestDto.getIsAuto() != null) advertisement.setIsAuto(advertisementRequestDto.getIsAuto());
        if (advertisementRequestDto.getColor() != null) advertisement.setColor(advertisementRequestDto.getColor());
        if (advertisementRequestDto.getHasDocuments() != null) advertisement.setHasDocuments(advertisementRequestDto.getHasDocuments());
        if (advertisementRequestDto.getNeedsRepair() != null) advertisement.setNeedsRepair(advertisementRequestDto.getNeedsRepair());
        if (advertisementRequestDto.getDescription() != null) advertisement.setDescription(advertisementRequestDto.getDescription());
        advertisement.setPrice(advertisementRequestDto.getPrice());
        if (advertisementRequestDto.getExchangePossible() != null) advertisement.setExchangePossible(advertisementRequestDto.getExchangePossible());
        advertisement.setStatus(advertisementRequestDto.getStatus());
        advertisement.setRegion(regionService.findRegionByName(advertisementRequestDto.getRegionName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Region Not Found")));
        advertisement.setCity(cityService.findCityByName(advertisementRequestDto.getCityName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: City Not Found")));
        advertisement.setPhone(advertisementRequestDto.getPhone());
        if (advertisementRequestDto.getAllowQuestions() != null) advertisement.setAllowQuestions(advertisementRequestDto.getAllowQuestions());
        if (advertisementRequestDto.getIsPaid() != null) advertisement.setIsPaid(advertisementRequestDto.getIsPaid());
        if (advertisementRequestDto.getImage() != null) advertisement.setImage(advertisementRequestDto.getImage());

        User user = userService.findUserByPhone(getCurrentUserPhone())
                .orElseThrow(() -> new ResourceNotFoundException("Error: User Not Found"));

        user.getAdvertisements().add(advertisement);

        userService.saveUser(user);

        return ResponseEntity.ok("Ad successfully added!");
    }

    private String getCurrentUserPhone() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
