package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.Region;

import java.util.Optional;

public interface RegionService {

    Optional<Region> findRegionByName(String name);

}
