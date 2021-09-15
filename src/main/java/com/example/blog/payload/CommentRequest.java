package com.example.blog.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class CommentRequest {
    @NotBlank
    private String body;
}
