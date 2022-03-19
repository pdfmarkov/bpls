package com.markovandkrivonosov.blps.services;

import com.markovandkrivonosov.blps.module.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByEmail(String email);

    List<User> findAllUserByRole(String role);

    User saveUser(User user);

}
