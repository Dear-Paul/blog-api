package com.example.blog.repositories;

import com.example.blog.enums.AccountStatus;
import com.example.blog.models.Connection;
import com.example.blog.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndPassword(String email, String passwprd);
    Optional<Users> deleteById(long userId);
    List<Users> findByAccountStatus(AccountStatus status);


}
