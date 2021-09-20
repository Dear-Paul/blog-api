package com.example.blog.servicesImpl;

import com.example.blog.exceptions.BlogApiException;
import com.example.blog.models.Comments;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.CommentRequest;
import com.example.blog.repositories.CommentsRepository;
import com.example.blog.repositories.PostsRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;




    @Override
    public Comments addCommentToPost(CommentRequest commentRequest, long postId, long userId) {
        Posts posts = postsRepository.getById(postId);
        Users users = userRepository.getById(userId);
        Comments comments = new Comments();
        comments.setPosts(posts);
        comments.setUsers(users);
        comments.setBody(commentRequest.getBody());

        return commentsRepository.save(comments);
    }

    @Override
    public Comments getComment(long postId, long id) {
        Posts posts = postsRepository.getById(postId);
        Comments comments = commentsRepository.getById(id);
        if (comments.getPosts().getId().equals(posts.getId())) {
            return comments;
        }
    throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
    }

    @Override
    public Comments updateComment(Comments comments) {
        return commentsRepository.save(comments);

    }

    @Override
    public void deleteComment(long id) {
        commentsRepository.deleteById(id);
    }

    @Override
    public Comments findCommentById(long id) {
        return commentsRepository.getById(id);
    }
}
