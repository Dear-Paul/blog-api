package com.example.blog.controllers;

import com.example.blog.models.Comments;
import com.example.blog.dto.PostDto;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.CommentRequest;
import com.example.blog.services.CommentService;
import com.example.blog.services.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/allPosts")
    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> posts = postService.getAllPosts();
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Success");
        return new ResponseEntity<>(posts, HttpStatus.ACCEPTED);


    }
    @GetMapping("/{id}")
    public ResponseEntity<Posts> getPostsById ( @PathVariable(value = "id") long id){
            return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);

        }

    @PostMapping("/save/{userId}")
    @ApiOperation(
            value = "Users posts their thoughts by userId",
            notes = "Let them hear you!",
            response = Posts.class)
    public ResponseEntity<Posts> makeAPost(@RequestBody PostDto postDto, @PathVariable(name = "userId") long id ){

        Posts posts1 = postService.addPost(postDto.getPost(), postDto.getTitle(), id);
        return new ResponseEntity<>(posts1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable(name = "id") long id, @RequestBody PostDto posts){
        Posts editPost = postService.getPostById(id);
        editPost.setPost(posts.getPost());
        editPost.setTitle(posts.getTitle());
        postService.updatePost(editPost);

        return new ResponseEntity<>(editPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Posts> deletePost(@PathVariable(value = "id") long id){
        postService.removePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/comment/{id}/{userId}")
    public ResponseEntity<Comments> addCommentToPost(@Valid @PathVariable(name = "id") long postId, @PathVariable(name = "userId") long userId, @RequestBody CommentRequest comment){
        Comments comments = commentService.addCommentToPost(comment, postId, userId);
        return new ResponseEntity<>(comments, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/{postId}/{userId}")
    public ResponseEntity<Comments> updateUserComment(@PathVariable(name = "id") long id, @RequestBody CommentRequest comment){
        Comments comments = commentService.findCommentById(id);
        comments.setBody(comment.getBody());
        return new ResponseEntity<>(commentService.updateComment(comments), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteUserComment(@PathVariable(name = "id") long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("This comment has been deleted", HttpStatus.ACCEPTED);
    }
    }

