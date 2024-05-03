package com.nicoals.DevNestBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicoals.DevNestBE.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}