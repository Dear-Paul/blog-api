package com.example.blog.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Connection {

    private static final long serialVersioUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(updatable = false)
    private LocalDateTime timeConnected;
    @ManyToOne
    private Users userConnection;
    @ManyToOne
    private Users user;

}
