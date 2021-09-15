package com.example.blog.services;

public interface LikeAndUnlikeService {
    Boolean likeAndUnlikePost(long postId, long userId);
    Boolean likeAndUnlikeComment(long commentId, long userId);
}
