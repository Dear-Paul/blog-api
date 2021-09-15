package com.example.blog.services;

import com.example.blog.models.Connection;
import com.example.blog.models.Posts;
import com.example.blog.payload.ApiResponse;

import java.util.List;

public interface PostService {
    List<Posts> getAllPosts();
    Posts getPostById(long id);
    Posts addPost(String posts, String title, long userId);
    void removePost(long id);
    void updatePost(Posts id);

}
