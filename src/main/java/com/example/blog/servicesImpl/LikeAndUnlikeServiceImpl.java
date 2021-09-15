package com.example.blog.servicesImpl;

import com.example.blog.exceptions.BlogApiException;
import com.example.blog.models.Comments;
import com.example.blog.models.LikeAndUnlike;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import com.example.blog.repositories.CommentsRepository;
import com.example.blog.repositories.LikeAndUnlikeRepository;
import com.example.blog.repositories.PostsRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.LikeAndUnlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeAndUnlikeServiceImpl implements LikeAndUnlikeService {

    @Autowired
    private LikeAndUnlikeRepository likeAndUnlikeRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Boolean likeAndUnlikePost(long postId, long userId) {
        Posts posts = postsRepository.getById(postId);
        Users users = userRepository.getById(userId);
        Boolean status;

        if(users==null){
            throw new BlogApiException(HttpStatus.NO_CONTENT, "User not found");
        } else if(posts==null){
            throw new BlogApiException(HttpStatus.NO_CONTENT, "Post does not exist");
        }
        else {
            Optional<LikeAndUnlike> userLike = likeAndUnlikeRepository.findByUsersAndPosts(users, posts);
            if(userLike.isPresent()){
                likeAndUnlikeRepository.delete(userLike.get());
                status=false;
            } else {
                LikeAndUnlike newLike = new LikeAndUnlike();
                newLike.setPosts(posts);
                newLike.setUsers(users);
                likeAndUnlikeRepository.save(newLike);
                status=true;
            }

        }
        return status;
    }

    @Override
    public Boolean likeAndUnlikeComment(long commentId, long userId) {
        Comments comments = commentsRepository.getById(commentId);
        Users users = userRepository.getById(userId);
        Boolean status;
        if(users==null){
            throw new BlogApiException(HttpStatus.BAD_GATEWAY, "User not found");
        } else if (comments==null){
            throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Comment does not exist");
        } else {
            Optional<LikeAndUnlike> userLike = likeAndUnlikeRepository.findByUsersAndComments(users, comments);
            if(userLike.isPresent()){
                likeAndUnlikeRepository.delete(userLike.get());
                status = false;
            }  else {
                LikeAndUnlike newLike = new LikeAndUnlike();
                newLike.setComments(comments);
                newLike.setUsers(users);
                status=true;
                likeAndUnlikeRepository.save(newLike);
            }
        }
        return status;
    }
}
