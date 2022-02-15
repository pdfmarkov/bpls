package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.City;

import java.util.Optional;

public interface CityService {

    Optional<City> findCityByName(String name);

}
