package com.example.blog.services;

import com.example.blog.models.Comments;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.CommentRequest;

public interface CommentService {
    Comments addCommentToPost(CommentRequest commentRequest, long postId, long userId);
    Comments getComment(long postId, long id);
    Comments updateComment(long postId, CommentRequest commentRequest, long userId);
    ApiResponse deleteComment(long postId, long id, long userId);
}
