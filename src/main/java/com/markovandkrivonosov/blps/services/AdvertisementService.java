package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.exceptions.ResourceNotFoundException;
import com.markovandkrivonosov.blps.module.Advertisement;
import com.markovandkrivonosov.blps.module.User;
import com.markovandkrivonosov.blps.module.requested.GrantRequestDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdvertisementService {

    List<Advertisement> findAllAdvertisementsBySpecification(Map<String, String> values, Boolean isAcceptable);

    Optional<Advertisement> findAdvertisementById(Long id);

    Advertisement saveAdvertisement(Advertisement advertisement);

    List<Advertisement> findAllAdvertisementByEmail(String email);

    void grantAdvertisementWithEmail(GrantRequestDto grantRequestDto);

    void addNewAdvertisementWithEmail(Advertisement advertisement);

}
