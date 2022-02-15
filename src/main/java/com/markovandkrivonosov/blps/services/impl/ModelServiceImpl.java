package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.Model;
import com.markovandkrivonosov.blps.repo.ModelRepository;
import com.markovandkrivonosov.blps.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Override
    public Optional<Model> findModelByName(String name) {
        return modelRepository.findByName(name);
    }
}
