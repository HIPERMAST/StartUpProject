package com.example.demo.apis;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Setter
@Getter
@Table(name = "books") // Specifies the name of the database table
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") // Specifies the column name in the table
    private String name;

    @Column(name = "description")
    private String description;

    // Constructors, getters, and setters

    // Default constructor
    public BookEntity() {
    }

    // Constructor with all fields
    public BookEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }



}
