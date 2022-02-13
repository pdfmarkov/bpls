package com.markovandkrivonosov.blps.services.impl;

import com.markovandkrivonosov.blps.module.User;
import com.markovandkrivonosov.blps.repo.UserRepository;
import com.markovandkrivonosov.blps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
