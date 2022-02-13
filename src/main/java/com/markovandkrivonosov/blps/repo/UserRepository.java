package com.markovandkrivonosov.blps.repo;

import com.markovandkrivonosov.blps.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    Optional<User> findById(Long id);

    Boolean existsByPhone(String phone);

}
