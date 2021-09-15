package com.example.blog.models;

import com.example.blog.enums.AccountStatus;
import com.example.blog.enums.UserType;
import com.example.blog.models.modelspayload.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users extends DateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    @Length(min = 5, message = "*Your username must be at least 5 characters")
    private String userName;
    @Length(min = 5, message = "*Your password must be at least 5 characters")
    private String password;
    @Length(min = 9, message = "*phone number must be at least 9 characters ")
    private String phoneNumber;
    @Column(unique = true, nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToOne
    private Connection connection;
    @OneToMany
    private List<Posts> posts;
    @OneToOne
    private Favourites favourites;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;





}
