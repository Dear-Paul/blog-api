package com.example.blog.servicesImpl;

import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import com.example.blog.repositories.PostsRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public Posts getPostById(long id) {
        return postsRepository.findById(id).get();
    }

    @Override
    public Posts addPost(String body, String title, long userId) {
        Posts posts = new Posts();
        Users users = userRepository.getById(userId);
        LocalDateTime timeCreated = LocalDateTime.now();
        posts.setPost(body);
        posts.setTitle(title);
        posts.setTimeCreated(timeCreated);
        posts.setUsers(users);

        return postsRepository.save(posts);
    }

    @Override
    public void removePost(long id) {
        postsRepository.deleteById(id);

    }

    @Override
    public void updatePost(Posts posts) {
        postsRepository.save(posts);

    }
}
