package com.example.blog.controllers;

import com.example.blog.payload.ApiResponse;
import com.example.blog.services.LikeAndUnlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/like")
public class LikeAndUnlikeController {

    @Autowired
    private LikeAndUnlikeService likeAndUnlikeService;

    @PostMapping("/{userId}/posts/{postId}")
    public ApiResponse likeAndUnlikePost(@PathVariable(name = "userId") long userId, @PathVariable(name = "postId") long postId){
        Boolean userLike = likeAndUnlikeService.likeAndUnlikePost(postId,userId);
        if(userLike){
            return new ApiResponse(Boolean.TRUE, "You successfully liked this post");
        } else {
            return new ApiResponse(Boolean.FALSE, "You successfully unliked this post");
        }
    }
    @PostMapping("/{userId}/{id}")
    public ApiResponse likeAndUnlikeComment(@PathVariable(name = "userId") long userId, @PathVariable(name = "id") long commentsId){
        Boolean userLike = likeAndUnlikeService.likeAndUnlikeComment(commentsId, userId);
        if(userLike){
            return new ApiResponse(Boolean.TRUE, "You successfully liked this comment");
        } else {
            return new ApiResponse(Boolean.FALSE, "Comment unliked");
        }
    }
}
