package com.github.carlosrelter.clientAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Entity
@Table(name="client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private Long id;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="cellphone", length = 15, nullable = false)
    private String cellphone;

    @Column(name="email", length = 100,nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="type",length = 10, nullable = false)
    private TypeClient type;

}
