package com.example.blog.services;

import com.example.blog.models.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Users> findByUserName(String userName);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndPassword(String email, String password);
    Users saveUser(Users users);
    List<Users> getAllUsers();
    void deleteUserById(long user) throws InterruptedException;
    void removeUserBySchedule() throws InterruptedException;
    Users restoreUserAccount(long userId);
}
