package com.example.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Instant joinedAt;
    private String email;
    private Long postCount;
    private String phone;
    private String password;
}
