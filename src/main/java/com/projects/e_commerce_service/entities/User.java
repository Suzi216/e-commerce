package com.projects.e_commerce_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;


    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private RoleEnum role;
    private String firstName;
    private String lastName;


}
