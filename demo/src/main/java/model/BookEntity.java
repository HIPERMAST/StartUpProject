package model;

import jakarta.persistence.*;

@Entity
@Table(name = "books") // Specifies the name of the database table
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") // Specifies the column name in the table
    private String name;

    @Column(name = "description")
    private String description;

    // Constructors, getters, and setters (if not generated by an IDE)

    // Default constructor (required by JPA)
    public BookEntity() {
    }

    // Constructor with all fields (if needed)
    public BookEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and setters for id, name, and description
    // ...

    // Additional methods (if needed)
}
