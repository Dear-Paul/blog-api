package com.example.blog.services;

import com.example.blog.models.Favourites;
import com.example.blog.models.Posts;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.PostResponse;

import java.util.List;

public interface FavouritesServices {
   List<Favourites> getAllFavoritesById(long favId);
   Favourites addToFavourites(long postId, long id);
   void removeFromFavourites(long id, long postId);
}
