package com.example.blog.repositories;

import com.example.blog.models.Comments;
import com.example.blog.models.LikeAndUnlike;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeAndUnlikeRepository extends JpaRepository<LikeAndUnlike, Long> {
    Optional<LikeAndUnlike> findByUsersAndComments(Users users, Comments comments);
    Optional<LikeAndUnlike> findByUsersAndPosts(Users users, Posts posts);
}
