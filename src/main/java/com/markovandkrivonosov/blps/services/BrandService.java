package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.Brand;

import java.util.Optional;

public interface BrandService {

    Optional<Brand> findBrandByName(String name);

}
