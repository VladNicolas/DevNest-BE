package com.nicoals.DevNestBE.service;

import java.util.List;

import com.nicoals.DevNestBE.model.Comment;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String content) throws Exception;
    void deleteComment(Long commentId, Long userId) throws Exception;
    List<Comment> findCommentByIssueId(Long issueId);
}
