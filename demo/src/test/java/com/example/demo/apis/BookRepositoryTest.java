package com.example.demo.apis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindByNameAndDescription() {
        // Save a sample book to the database
        BookEntity savedBook = bookRepository.save(new BookEntity("Sample Book", "Description"));

        // Search for the saved book by name and description
        BookEntity foundBook = bookRepository.findByNameAndDescription("Sample Book", "Description");

        // Verify that the book is found and is not null
        assertNotNull(foundBook);
        assertEquals(savedBook.getId(), foundBook.getId());
        assertEquals(savedBook.getName(), foundBook.getName());
        assertEquals(savedBook.getDescription(), foundBook.getDescription());
    }
}

