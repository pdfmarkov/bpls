package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.Advertisement;
import com.markovandkrivonosov.blps.repo.AdvertisementRepository;
import com.markovandkrivonosov.blps.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> findAllAdvertisements() {
        return advertisementRepository.findAll();
    }
}
