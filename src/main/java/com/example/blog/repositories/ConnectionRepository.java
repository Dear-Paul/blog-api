package com.example.blog.repositories;

import com.example.blog.models.Connection;
import com.example.blog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    Optional<Connection> findByUserAndUserConnection(Users users, Users userConnect);
    List<Connection> findConnectionsByUser(Users user);

}
