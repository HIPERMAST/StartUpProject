package controller;

import model.BookEntity;
import org.springframework.web.bind.annotation.*;
import repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
public class BookController {

    private final BookRepository bookRepository;
    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/book")
    public BookEntity createBook(@RequestBody BookEntity book) {
        return bookRepository.save(book);
    }

    @GetMapping("/book/{id}")
    public BookEntity getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @GetMapping("/books")
    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/hola")
    public String getBooks1() {
        return "Hello World!";
    }

}
