package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.Advertisement;
import com.markovandkrivonosov.blps.repo.AdvertisementRepository;
import com.markovandkrivonosov.blps.services.AdvertisementService;
import com.markovandkrivonosov.blps.specifications.AdvertisementWithBrandName;
import com.markovandkrivonosov.blps.specifications.AdvertisementWithCityName;
import com.markovandkrivonosov.blps.specifications.AdvertisementWithRegionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> findAllAdvertisementsBySpecification(Map<String, String> values) {
        return advertisementRepository.findAll(Specification
                .where(new AdvertisementWithBrandName(values.get("brand")))
                .and(new AdvertisementWithCityName(values.get("city")))
                .and(new AdvertisementWithRegionName(values.get("region"))));
    }

}
