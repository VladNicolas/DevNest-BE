package com.nicoals.DevNestBE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicoals.DevNestBE.model.Project;
import com.nicoals.DevNestBE.model.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByNameContainingAndTeamContains(String partialName, User user);
    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
