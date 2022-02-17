package com.markovandkrivonosov.blps.repo;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, JpaSpecificationExecutor<Advertisement> {

    List<Advertisement> findAllByBrandName(@NotBlank String brand);

    List<Advertisement> findAllByRegionName(@NotBlank String region);

    List<Advertisement> findAllByCityName(@NotBlank String city);

    List<Advertisement> findAllByBrandNameAndRegionName(@NotBlank String brand, @NotBlank String region);

    List<Advertisement> findAllByBrandNameAndCityName(@NotBlank String brand, @NotBlank String city);

    List<Advertisement> findAllByRegionNameAndCityName(@NotBlank String region, @NotBlank String city);

    List<Advertisement> findAllByRegionNameAndCityNameAndBrandName(@NotBlank String brand, @NotBlank String region, @NotBlank String city);

}