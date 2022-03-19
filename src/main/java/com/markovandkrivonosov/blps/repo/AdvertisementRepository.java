package com.markovandkrivonosov.blps.repo;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, JpaSpecificationExecutor<Advertisement> {

    List<Advertisement> findAllByUserEmail(String email);

}