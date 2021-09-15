package com.example.blog.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private String title;
    private String body;
    private List<String> favPosts;
}
