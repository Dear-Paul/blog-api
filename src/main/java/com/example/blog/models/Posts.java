package com.example.blog.models;

import com.example.blog.models.modelspayload.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime timeCreated;
    @ManyToOne
    private Users users;
    private String title;
    @OneToMany
    private List<Comments> commentsList;
    private String post;


}
