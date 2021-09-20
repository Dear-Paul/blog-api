package com.example.blog.services;

import com.example.blog.models.Comments;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.CommentRequest;

public interface CommentService {
    Comments addCommentToPost(CommentRequest commentRequest, long postId, long userId);
    Comments getComment(long postId, long id);
    Comments updateComment(Comments comment);
    void deleteComment(long id);
    Comments findCommentById(long id);
}
