package com.github.carlosrelter.clientAPI.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String password;


}
