package com.example.blog.controllers;

import com.example.blog.dto.PostDto;
import com.example.blog.models.Connection;
import com.example.blog.models.Posts;
import com.example.blog.payload.ApiResponse;
import com.example.blog.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/connections")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @PostMapping("/{mainId}/{id}")
    public ApiResponse connectionHandler(@PathVariable(name = "mainId") long userId, @PathVariable(name = "id") long id) {
        connectionService.addUserToConnections(userId, id);
        return new ApiResponse(Boolean.TRUE, "This user has been added to connections");

    }

    @GetMapping("/list/{id}")
    public ApiResponse displayPostsByConnection(@PathVariable(name = "id") long id) {
        List<Posts> connectionPosts = connectionService.displayPostsByConnection(id);
        if (!connectionPosts.isEmpty()) {
            return new ApiResponse(HttpStatus.ACCEPTED, connectionPosts);
        } else {
            return new ApiResponse("There is no post avialable", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/connections/{id}")
    public ResponseEntity<List<Connection>> displayAllUserConnection(@PathVariable(name = "id") long userId){
        List<Connection> userConnections = connectionService.displayUserConnection(userId);
        return new ResponseEntity<>(userConnections, HttpStatus.ACCEPTED);
    }
}
