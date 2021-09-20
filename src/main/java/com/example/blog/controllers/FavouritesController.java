package com.example.blog.controllers;

import com.example.blog.models.Favourites;
import com.example.blog.models.Posts;
import com.example.blog.payload.ApiResponse;
import com.example.blog.repositories.FavouritesRepository;
import com.example.blog.services.FavouritesServices;
import com.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

import java.util.List;


@RestController
@RequestMapping("/api/vi/fav")
public class FavouritesController {

    @Autowired
    private PostService postService;

    @Autowired
    private FavouritesServices favouritesServices;


    @GetMapping("/getFavPosts/{userId}")
    public ResponseEntity<List<Favourites>> getAllFavPost(@PathVariable(name = "userId") long favId){
        List<Favourites> favPosts = favouritesServices.getAllFavoritesById(favId);
        return new ResponseEntity<>(favPosts, HttpStatus.OK);
    }

    @PostMapping("/addToFavourites/{id}/save/{userId}")
    public ApiResponse addToFavourites(@PathVariable(name = "id") long postId, @PathVariable(name = "userId") long userId){
        favouritesServices.addToFavourites(postId, userId);
        return new ApiResponse(Boolean.TRUE, "You successfully added the post to favourites");

    }

    @PostMapping("/removeFromFav/{id}/del/{postId}")
    public ApiResponse removeFromFavourites(@PathVariable(name = "id") long userId, @PathVariable(name = "postId") long postId){
        favouritesServices.removeFromFavourites(userId, postId);
        return new ApiResponse(Boolean.TRUE, "Posts successfully removed from favourites");
    }
}
