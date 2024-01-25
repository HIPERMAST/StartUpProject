package com.example.demo.apis;

public class BookDTO {
    private Long id;
    private String name;

    // Constructors, getters, and setters
    public BookDTO() {
    }

    public BookDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

