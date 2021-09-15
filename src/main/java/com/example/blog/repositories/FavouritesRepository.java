package com.example.blog.repositories;

import com.example.blog.models.Favourites;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
//    Favourites getFavouritesByUsers_Id(long userId);
    Optional<Favourites> findByUsers(Users users);
    Favourites findAllById(long favId);

}
