package com.example.blog.payload;

import lombok.Data;

@Data
public class UserSummary {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
}
