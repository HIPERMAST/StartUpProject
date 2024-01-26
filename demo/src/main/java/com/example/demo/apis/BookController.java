package com.example.demo.apis;

import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private final BookRepository bookRepository;

    /*
    private final AmazonSQS amazonSQS;
    private final String queueUrl;
    */

    @Autowired
    public BookController(BookRepository bookRepository/*, AmazonSQS amazonSQS, @Value("${aws.sqs.queue-url}") String queueUrl*/) {
        this.bookRepository = bookRepository;
    /*
        this.amazonSQS = amazonSQS;
        this.queueUrl = queueUrl;
    */
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @PostMapping("/book")
    public ResponseEntity<String> createBook(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String description = request.get("description");

        // Validate that the required fields are present
        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name and description are required");
        }

        // Check if a book with the same name and description already exists
        BookEntity bookExists = bookRepository.findByNameAndDescription(name, description);

        if (bookExists != null) {
            // Update the existing book's attributes
            bookExists.setName(name);
            bookExists.setDescription(description);
            BookEntity updatedBook = bookRepository.save(bookExists);

            // Return a response indicating that the book has been updated
            return ResponseEntity.status(HttpStatus.OK).body("The book with id: " + updatedBook.getId() + " has been updated.");
        } else {
            // Create a new BookEntity object and save it
            BookEntity book = new BookEntity(name, description, 0.0f);
            BookEntity savedBook = bookRepository.save(book);

            /*
            // Send a message to the queue to indicate that a new book has been created
            amazonSQS.sendMessage(queueUrl, "A new book with id: " + savedBook.getId() + " has been created.");
            */

            // Return a response indicating that a new book has been created
            return ResponseEntity.status(HttpStatus.CREATED).body("A new book with id: " + savedBook.getId() + " has been created." );
        }
    }



    @GetMapping("/book/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable Long id) {

        // Find the book with the given id
        BookEntity book = bookRepository.findById(id)
                .orElse(null);

        // If the book is not found, return a 404 Not Found response
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping("/books")
    public List<BookDTO> getBooks() {
        List<BookEntity> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookDTO(book.getId(), book.getName()))
                .toList();
    }
}
