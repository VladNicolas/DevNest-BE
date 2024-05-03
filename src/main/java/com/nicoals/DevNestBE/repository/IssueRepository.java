package com.nicoals.DevNestBE.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicoals.DevNestBE.model.Issue;


public interface IssueRepository extends JpaRepository<Issue, Long> {

    public List<Issue> findByProjectId(Long id);
}