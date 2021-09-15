package com.example.blog.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikeAndUnlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Posts posts;
    @OneToOne
    private Users users;
    @ManyToOne
    private Comments comments;

    }

