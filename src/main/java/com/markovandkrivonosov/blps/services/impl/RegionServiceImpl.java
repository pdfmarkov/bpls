package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.Region;
import com.markovandkrivonosov.blps.repo.RegionRepository;
import com.markovandkrivonosov.blps.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionRepository regionRepository;

    @Override
    public Optional<Region> findRegionByName(String name) {
        return regionRepository.findByName(name);
    }
}
