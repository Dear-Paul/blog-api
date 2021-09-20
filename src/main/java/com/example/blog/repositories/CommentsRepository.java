package com.example.blog.repositories;

import com.example.blog.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Comments findCommentsByIdAndAndPostsAndUsers(long commentId, long postId, long userId);
}
