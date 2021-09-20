package com.example.blog;


import com.example.blog.controllers.UserController;
import com.example.blog.exceptions.BlogApiException;
import com.example.blog.models.Users;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.UserService;
import com.example.blog.servicesImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private MockMvc mvc;

//    @MockBean
//    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Users> userList;

    private String uri = "/api/v1/users";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceCheck;



    // @BeforeEach
    void setUp(){
        this.userList = new ArrayList<>();
        this.userList.add(new Users(1L, "123456" , "paul@gmail.com"));
        this.userList.add(new Users(2L, "1234er", "enpower@gmail.com"));
        this.userList.add(new Users(3L, "222222", "lesley@gmail.com"));
    }

//    @Test
//    void testGetAllUsers() throws Exception{
//        given(userService.getAllUsers(1L)).willReturn(userList);
//        this.mvc.perform(get(uri))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size", is(userList.size())));
//
//    }

    @Test
    void testRegisterUser(){
        final Users user = new Users(1L, "Hello", "Odun", "olopa", "123456", "08154321234", "blessed@gmail");

        //given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        //when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);
        when(userRepository.save(user)).then(invocation -> invocation.getArgument(0));
        Users savedUser = userServiceCheck.saveUser(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isEqualTo(user);

        verify(userRepository).save(any(Users.class));
    }

    @Test
    void testShouldThrowExceptionWhenUserEmailIsNull(){
        final Users user = new Users(1L, "Hello", "Odun", "olopa", "123456", "08154321234", null);
       // given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        assertThrows(BlogApiException.class, () ->{
            userServiceCheck.saveUser(user);
        });
        verify(userRepository, never()).save(any(Users.class));
    }

}
