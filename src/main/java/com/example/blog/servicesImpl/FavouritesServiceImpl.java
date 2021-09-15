package com.example.blog.servicesImpl;

import com.example.blog.models.Favourites;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.PostResponse;
import com.example.blog.repositories.FavouritesRepository;
import com.example.blog.repositories.PostsRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.FavouritesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FavouritesServiceImpl implements FavouritesServices {

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private PostsRepository postsRepository;


    @Override
    public List<Favourites> getAllFavoritesById(long favId) {

        Favourites favourites =  favouritesRepository.findAllById(favId);
        List<Favourites> listOfFavourites = new ArrayList<>();
        listOfFavourites.add(favourites);
        return listOfFavourites;
    }

    @Override
    public Favourites addToFavourites(long postId, long id) {
        Favourites saveFavourites = new Favourites();
        Users users = userRepository.getById(id);
        Posts posts = postsRepository.getById(postId);
        Optional<Favourites> favourites = favouritesRepository.findByUsers(users);
        if (favourites.isEmpty()) {
            Favourites favourites1 = new Favourites();
            favourites1.setUsers(users);
            List<Posts> listOfPosts = new ArrayList<>();
            listOfPosts.add(posts);
            favourites1.setFavPosts(listOfPosts);
            saveFavourites = favouritesRepository.save(favourites1);
        } else {
            List<Posts> listsOfPost = favourites.get().getFavPosts();
            listsOfPost.add(posts);
            favourites.get().setFavPosts(listsOfPost);
            saveFavourites = favouritesRepository.save(favourites.get());
        }
        return saveFavourites;
    }

    @Override
    public void removeFromFavourites(long id, long postId) {
        Favourites favourites = favouritesRepository.getById(id);
        List<Posts> listOfFavourites = favourites.getFavPosts();
        //listOfFavourites.add(favourites);
        Posts posts = postsRepository.getById(postId);
        for (Posts post:listOfFavourites) {
            if(post.equals(posts)){
                listOfFavourites.remove(posts);
                favourites.setFavPosts(listOfFavourites);
                favouritesRepository.save(favourites);
            }

        }

    }
}

