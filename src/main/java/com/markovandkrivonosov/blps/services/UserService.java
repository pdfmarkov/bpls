package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByPhone(String phone);

    User saveUser(User user);

}
