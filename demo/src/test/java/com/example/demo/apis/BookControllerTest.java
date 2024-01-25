package com.example.demo.apis;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;


    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBook() {
        // Create a sample book request
        Map<String, String> bookRequest = new HashMap<>();
        bookRequest.put("name", "Sample Book");
        bookRequest.put("description", "Description");

        // Mock the bookRepository behavior
        when(bookRepository.findByNameAndDescription("Sample Book", "Description")).thenReturn(null);

        // Create a book entity and set its ID (simulating the database)
        BookEntity savedBook = new BookEntity("Sample Book", "Description");
        savedBook.setId(1L);

        // Mock the bookRepository.save behavior
        when(bookRepository.save(any(BookEntity.class))).thenReturn(savedBook);

        // Make a POST request to create a book
        ResponseEntity<String> response = bookController.createBook(bookRequest);

        // Verify the response status and message
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("A new book with id: 1 has been created.", response.getBody());
    }

    @Test
    void testUpdateExistingBook() {
        // Create a sample book request
        Map<String, String> bookRequest = new HashMap<>();
        bookRequest.put("name", "Sample Book");
        bookRequest.put("description", "Description");

        // Mock the bookRepository behavior
        BookEntity existingBook = new BookEntity("Sample Book", "Description");
        existingBook.setId(1L);
        when(bookRepository.findByNameAndDescription("Sample Book", "Description")).thenReturn(existingBook);

        // Mock the bookRepository.save behavior
        when(bookRepository.save(any(BookEntity.class))).thenReturn(existingBook);

        // Make a POST request to update an existing book
        ResponseEntity<String> response = bookController.createBook(bookRequest);

        // Verify the response status and message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("The book with id: 1 has been updated.", response.getBody());
    }

    @Test
    void testGetBookById() {
        // Create a sample book entity
        BookEntity book = new BookEntity("Sample Book", "Description");
        book.setId(1L);

        // Mock the bookRepository.findById behavior
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));

        // Make a GET request to retrieve a book by ID
        ResponseEntity<BookEntity> response = bookController.getBook(1L);

        // Verify the response status and content
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void testGetBookByIdNotFound() {
        // Mock the bookRepository.findById behavior for a non-existing book
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Make a GET request to retrieve a non-existing book by ID
        ResponseEntity<BookEntity> response = bookController.getBook(1L);

        // Verify the response status (should be 404 Not Found)
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetBooks() {
        // Create sample book entities
        List<BookEntity> books = List.of(
                new BookEntity("Book 1", "Description 1"),
                new BookEntity("Book 2", "Description 2")
        );

        // Mock the bookRepository.findAll behavior
        when(bookRepository.findAll()).thenReturn(books);

        // Make a GET request to retrieve all books
        List<BookDTO> response = bookController.getBooks();

        // Verify the response content
        assertEquals(books.size(), response.size());
        assertEquals(books.get(0).getId(), response.get(0).getId());
        assertEquals(books.get(0).getName(), response.get(0).getName());
        assertEquals(books.get(1).getId(), response.get(1).getId());
        assertEquals(books.get(1).getName(), response.get(1).getName());
    }
}

