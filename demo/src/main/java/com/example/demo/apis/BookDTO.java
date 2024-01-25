package com.example.demo.apis;

import lombok.Getter;

@Getter
public class BookDTO {
    final Long id;
    final String name;

    // Constructors, getters, and setters

    public BookDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}

