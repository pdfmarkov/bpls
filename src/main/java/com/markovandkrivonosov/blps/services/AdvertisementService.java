package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.Advertisement;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

public interface AdvertisementService {

    List<Advertisement> findAllAdvertisementsBySpecification(Map<String, String> values);

}
