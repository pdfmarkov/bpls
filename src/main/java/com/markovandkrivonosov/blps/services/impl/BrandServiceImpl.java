package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.Brand;
import com.markovandkrivonosov.blps.repo.BrandRepository;
import com.markovandkrivonosov.blps.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Optional<Brand> findBrandByName(String name) {
        return brandRepository.findByName(name);
    }
}
