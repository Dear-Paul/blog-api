package com.example.blog.servicesImpl;

import com.example.blog.exceptions.BlogApiException;
import com.example.blog.models.Connection;
import com.example.blog.models.Posts;
import com.example.blog.models.Users;
import com.example.blog.repositories.ConnectionRepository;
import com.example.blog.repositories.PostsRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;


    @Override
    public Connection addUserToConnections(long userId, long id) {
        Users users = userRepository.getById(id);
        Users mainUser = userRepository.getById(userId);
        LocalDateTime timeUserConnected = LocalDateTime.now();
        Boolean status;
        Optional<Connection> connection = connectionRepository.findByUserAndUserConnection(mainUser, users);
        if(!connection.isPresent()){
            Connection newConnection = new Connection();
            newConnection.setTimeConnected(timeUserConnected);
            newConnection.setUserConnection(users);
            newConnection.setUser(mainUser);
             return connectionRepository.save(newConnection);
        } else {


//            List<Users> listOfConnections = connection.get().getUsers();
//            listOfConnections.add(users);
          throw new BlogApiException("The users are connected already");


        }

    }

    @Override
    public List<Posts> displayPostsByConnection(long userId) {
        Users user = userRepository.getById(userId);
       List<Connection> connection = connectionRepository.findConnectionsByUser(user);
        List<Posts> listOfPosts = new ArrayList<>();
        if(!connection.isEmpty()){

            for (Connection userConnection:connection) {
                Users users = userConnection.getUserConnection();
                List<Posts> posts = postsRepository.findPostsByUsers(users);
                listOfPosts.addAll(posts);
            }
        }
        return listOfPosts;
    }

    @Override
    public List<Connection> displayUserConnection(long userId) {
        Users user = userRepository.getById(userId);

        return connectionRepository.findConnectionsByUser(user);
    }


}
