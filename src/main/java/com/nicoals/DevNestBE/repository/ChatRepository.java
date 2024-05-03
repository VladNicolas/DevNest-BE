package com.nicoals.DevNestBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicoals.DevNestBE.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}
