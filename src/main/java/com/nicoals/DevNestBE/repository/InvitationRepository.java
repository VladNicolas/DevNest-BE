package com.nicoals.DevNestBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicoals.DevNestBE.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{

    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);
}