package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<Advertisement> findAllAdvertisements();
}
