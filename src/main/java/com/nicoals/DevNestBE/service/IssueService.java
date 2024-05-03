package com.nicoals.DevNestBE.service;

import java.util.List;

import com.nicoals.DevNestBE.model.Issue;
import com.nicoals.DevNestBE.model.User;
import com.nicoals.DevNestBE.request.IssueRequest;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;
    List<Issue> getIssueByProjectId (Long projectId) throws Exception;
    Issue createIssue(IssueRequest issueRequest, User user) throws Exception;
    void deleteIssue(Long issueId, Long userId) throws Exception;
    Issue addUserToIssue(Long issueId, Long userId) throws Exception;
    Issue updateStatus(Long issueId, String status) throws Exception;
}