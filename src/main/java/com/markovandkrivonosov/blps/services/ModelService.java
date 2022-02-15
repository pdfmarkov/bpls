package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.Model;

import java.util.Optional;

public interface ModelService {

    Optional<Model> findModelByName(String name);

}
