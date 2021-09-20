package com.example.blog.servicesImpl;

import com.example.blog.exceptions.NoContentFoundException;
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
    public List<Favourites> getAllFavoritesById(long userId) {

        Users user = userRepository.getById(userId);

//        Favourites favourites =  favouritesRepository.getById(user);
        List<Favourites> listOfFavourites = favouritesRepository.getFavouritesByUsers_Id(user.getId());

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
       // Favourites favourites = new Favourites();
        Users user = userRepository.getById(id);
        Posts userPost = postsRepository.getById(postId);
        Favourites favourites = favouritesRepository.findFavouritesByUsers(user).get();

        if(userPost==null){
            throw new NoContentFoundException("Post not found");
        } else {
            List<Posts> listOfPost = favourites.getFavPosts();

            for (Posts post : listOfPost) {
                if (post.equals(userPost)) {
                    listOfPost.remove(post);
                    favourites.setFavPosts(listOfPost);

                }

            }
            favouritesRepository.save(favourites);
        }

        }
    }



