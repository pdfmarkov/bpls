package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.City;
import com.markovandkrivonosov.blps.repo.CityRepository;
import com.markovandkrivonosov.blps.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public Optional<City> findCityByName(String name) {
        return cityRepository.findByName(name);
    }
}
