package com.example.blog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class Favourites {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany(targetEntity = Posts.class)
    private List<Posts> favPosts;
    @OneToOne
    private Users users;




}
