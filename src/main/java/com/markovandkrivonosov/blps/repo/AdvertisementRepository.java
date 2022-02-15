package com.markovandkrivonosov.blps.repo;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}