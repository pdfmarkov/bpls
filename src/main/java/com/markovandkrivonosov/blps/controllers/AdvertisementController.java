package com.markovandkrivonosov.blps.controllers;


import com.markovandkrivonosov.blps.exceptions.ResourceIsNotValidException;
import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.*;
import com.markovandkrivonosov.blps.module.requested.AdvertisementRequestDto;
import com.markovandkrivonosov.blps.module.responses.AdvertisementResponseDto;
import com.markovandkrivonosov.blps.module.responses.UserAdvertisementResponseDto;
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> searchAllAdvertisements(@RequestParam Map<String,String> values) {

        if (values.get("page") == null || values.get("size") == null ||
                Integer.parseInt(values.get("page")) < 0 || Integer.parseInt(values.get("size")) <= 0 )
            throw new ResourceIsNotValidException("Error: page and size are necessarily and must be positive");

        List<Advertisement> trueAdvertisementList = advertisementService.findAllAdvertisementsBySpecification(values, true);

        List<AdvertisementResponseDto> advertisementResponseDtoList = new ArrayList<>();
        getAdvertisementResponseDtoList(trueAdvertisementList, advertisementResponseDtoList);

        for (GrantedAuthority grantedAuthority : ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities()) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {

                List<Advertisement> nullAdvertisementList = new ArrayList<>(advertisementService.findAllAdvertisementsBySpecification(values, null));
                getAdvertisementResponseDtoList(nullAdvertisementList, advertisementResponseDtoList);

                List<Advertisement> falseAdvertisementList = new ArrayList<>(advertisementService.findAllAdvertisementsBySpecification(values, false));
                getAdvertisementResponseDtoList(falseAdvertisementList, advertisementResponseDtoList);

                break;
            }
        }

        return ResponseEntity.ok(advertisementResponseDtoList);
    }

    @GetMapping(value = "/my")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserAdvertisements() {

        List<Advertisement> advertisementList = advertisementService.findAllAdvertisementByEmail(getCurrentEmail());

        if (advertisementList.isEmpty())
            return ResponseEntity.ok("You haven't any ads ;(");

        List<UserAdvertisementResponseDto> userAdvertisementResponseDtoList = new ArrayList<>();
        getUserAdvertisementResponseDtoList(advertisementList, userAdvertisementResponseDtoList);

        return ResponseEntity.ok(userAdvertisementResponseDtoList);
    }

    @PutMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addNewAdvertisement(@Valid @RequestBody AdvertisementRequestDto advertisementRequestDto) {

        Advertisement advertisement = new Advertisement();

        advertisement.setVin(advertisementRequestDto.getVin());
        if (advertisementRequestDto.getSts() != null) advertisement.setSts(advertisementRequestDto.getSts());
        if (advertisementRequestDto.getStateNumber() != null) advertisement.setStateNumber(advertisementRequestDto.getStateNumber());
        advertisement.setBrand(brandService.findBrandByName(advertisementRequestDto.getBrandName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Brand Not Found")));
        Model model = modelService.findModelByName(advertisementRequestDto.getModelName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: Model Not Found"));
        if (advertisementRequestDto.getBrandName().equals(model.getBrand().getName())){
            advertisement.setModel(model);
        } else {
            throw new ResourceNotFoundException("Error: This brand doesn't have this model");
        }
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
        City city = cityService.findCityByName(advertisementRequestDto.getCityName())
                .orElseThrow(() -> new ResourceNotFoundException("Error: City Not Found"));
        if (advertisementRequestDto.getRegionName().equals(city.getRegion().getName())){
            advertisement.setCity(city);
        } else {
            throw new ResourceNotFoundException("Error: This region doesn't have this city");
        }
        advertisement.setPhone(advertisementRequestDto.getPhone());
        if (advertisementRequestDto.getAllowQuestions() != null) advertisement.setAllowQuestions(advertisementRequestDto.getAllowQuestions());
        if (advertisementRequestDto.getIsPaid() != null) advertisement.setIsPaid(advertisementRequestDto.getIsPaid());
        if (advertisementRequestDto.getImage() != null) advertisement.setImage(advertisementRequestDto.getImage());
        advertisement.setAcceptable(null);

        advertisementService.addNewAdvertisementWithEmail(advertisement);

        return ResponseEntity.ok("Ad successfully added!");
    }


    private String getCurrentEmail() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    private void getAdvertisementResponseDtoList(List<Advertisement> trueAdvertisementList, List<AdvertisementResponseDto> advertisementResponseDtoList) {
        AdvertisementResponseDto advertisementResponseDto;
        for (Advertisement advertisement : trueAdvertisementList) {
            advertisementResponseDto = new AdvertisementResponseDto();
            advertisementResponseDto.setVin(advertisement.getVin());
            advertisementResponseDto.setSts(advertisement.getSts());
            advertisementResponseDto.setStateNumber(advertisement.getStateNumber());
            advertisementResponseDto.setBrandName(advertisement.getBrand().getName());
            advertisementResponseDto.setModelName(advertisement.getModel().getName());
            advertisementResponseDto.setMileage(advertisement.getMileage());
            advertisementResponseDto.setIsRight(advertisement.getIsRight());
            advertisementResponseDto.setBody(advertisement.getBody());
            advertisementResponseDto.setReleaseYear(advertisement.getReleaseYear());
            advertisementResponseDto.setDriveUnit(advertisement.getDriveUnit());
            advertisementResponseDto.setFuel(advertisement.getFuel());
            advertisementResponseDto.setIsAuto(advertisement.getIsAuto());
            advertisementResponseDto.setColor(advertisement.getColor());
            advertisementResponseDto.setHasDocuments(advertisement.getHasDocuments());
            advertisementResponseDto.setNeedsRepair(advertisement.getNeedsRepair());
            advertisementResponseDto.setDescription(advertisement.getDescription());
            advertisementResponseDto.setPrice(advertisement.getPrice());
            advertisementResponseDto.setExchangePossible(advertisement.getExchangePossible());
            advertisementResponseDto.setStatus(advertisement.getStatus());
            advertisementResponseDto.setRegionName(advertisement.getRegion().getName());
            advertisementResponseDto.setCityName(advertisement.getCity().getName());
            advertisementResponseDto.setPhone(advertisement.getPhone());
            advertisementResponseDto.setAllowQuestions(advertisement.getAllowQuestions());
            advertisementResponseDto.setIsPaid(advertisement.getIsPaid());
            advertisementResponseDto.setImage(advertisement.getImage());
            advertisementResponseDtoList.add(advertisementResponseDto);
        }
    }

    private void getUserAdvertisementResponseDtoList(List<Advertisement> trueAdvertisementList, List<UserAdvertisementResponseDto> userAdvertisementResponseDtoList) {
        UserAdvertisementResponseDto userAdvertisementResponseDto;
        for (Advertisement advertisement : trueAdvertisementList) {
            userAdvertisementResponseDto = new UserAdvertisementResponseDto();
            userAdvertisementResponseDto.setVin(advertisement.getVin());
            userAdvertisementResponseDto.setSts(advertisement.getSts());
            userAdvertisementResponseDto.setStateNumber(advertisement.getStateNumber());
            userAdvertisementResponseDto.setBrandName(advertisement.getBrand().getName());
            userAdvertisementResponseDto.setModelName(advertisement.getModel().getName());
            userAdvertisementResponseDto.setMileage(advertisement.getMileage());
            userAdvertisementResponseDto.setIsRight(advertisement.getIsRight());
            userAdvertisementResponseDto.setBody(advertisement.getBody());
            userAdvertisementResponseDto.setReleaseYear(advertisement.getReleaseYear());
            userAdvertisementResponseDto.setDriveUnit(advertisement.getDriveUnit());
            userAdvertisementResponseDto.setFuel(advertisement.getFuel());
            userAdvertisementResponseDto.setIsAuto(advertisement.getIsAuto());
            userAdvertisementResponseDto.setColor(advertisement.getColor());
            userAdvertisementResponseDto.setHasDocuments(advertisement.getHasDocuments());
            userAdvertisementResponseDto.setNeedsRepair(advertisement.getNeedsRepair());
            userAdvertisementResponseDto.setDescription(advertisement.getDescription());
            userAdvertisementResponseDto.setPrice(advertisement.getPrice());
            userAdvertisementResponseDto.setExchangePossible(advertisement.getExchangePossible());
            userAdvertisementResponseDto.setStatus(advertisement.getStatus());
            userAdvertisementResponseDto.setRegionName(advertisement.getRegion().getName());
            userAdvertisementResponseDto.setCityName(advertisement.getCity().getName());
            userAdvertisementResponseDto.setPhone(advertisement.getPhone());
            userAdvertisementResponseDto.setAllowQuestions(advertisement.getAllowQuestions());
            userAdvertisementResponseDto.setIsPaid(advertisement.getIsPaid());
            userAdvertisementResponseDto.setImage(advertisement.getImage());
            userAdvertisementResponseDto.setAcceptable(advertisement.getAcceptable());
            userAdvertisementResponseDtoList.add(userAdvertisementResponseDto);
        }
    }

}
