package com.example.blog.services;

import com.example.blog.models.Connection;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;

import java.util.List;

public interface ConnectionService {
    Connection addUserToConnections(long userId, long id);
    List<Posts> displayPostsByConnection(long userId);
}
