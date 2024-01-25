package com.example.demo.apis;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "books") // Specifies the name of the database table
public class BookEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "name") // Specifies the column name in the table
    private String name;

    @Setter
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


    // Override toString method to provide a meaningful representation
    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
