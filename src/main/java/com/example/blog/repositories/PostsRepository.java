package com.example.blog.repositories;

import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findPostsByUsers(Users user);
    Optional<Posts> findByUsersAndPost(long userId, long postId);
}
