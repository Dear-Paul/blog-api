package com.example.blog.controllers;


import com.example.blog.dto.UserDto;
import com.example.blog.exceptions.NoContentFoundException;
import com.example.blog.models.Users;
import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.UserProfile;
import com.example.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Users> userRegistration(@RequestBody UserProfile user){
        Users newUser = new Users();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhoneNumber(user.getPhone());
        newUser.setCreatedAt(user.getJoinedAt());
        return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ApiResponse userLogin(@Valid @RequestBody UserDto userLogin){
      Optional<Users> user = userService.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
      if(user.isPresent()){
          return new ApiResponse(Boolean.TRUE, "You are logged in as user", user);
      } else {
          return new ApiResponse("User not found", HttpStatus.NO_CONTENT);
      }
//        return user.map(users -> new ResponseEntity<>(users, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(user.get(), HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUserAccount(@PathVariable(name = "id") long userId) throws InterruptedException {
        userService.deleteUserById(userId);
        return new ApiResponse("This user will be deleted after one minute", HttpStatus.NO_CONTENT );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> restoreUserAccount(@PathVariable(name = "id") long userId, @RequestBody UserProfile profile){
        Users user = userService.restoreUserAccount(userId);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Users>> viewAllUsers(@PathVariable(name = "id") long userId){
        List<Users> allUsers = userService.getAllUsers(userId);
        return new ResponseEntity<>(allUsers, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Users> updateUser(@PathVariable(name = "username") String userName, @RequestBody @Valid  UserProfile profile){
        Users user = userService.findByUserName(userName);
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        user.setUserName(profile.getUserName());
        user.setPassword(profile.getPassword());
        user.setEmail(profile.getEmail());
        user.setPhoneNumber(profile.getPhone());
        user.setUpdateAt(profile.getJoinedAt());
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
    }
    }



