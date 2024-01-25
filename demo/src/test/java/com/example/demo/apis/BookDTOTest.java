package com.example.demo.apis;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDTOTest {

    @Test
    void testBookDTOConstructor() {
        // Create a BookDTO instance
        BookDTO bookDTO = new BookDTO(1L, "Sample Book");

        // Verify that the constructor sets the values correctly
        assertEquals(1L, bookDTO.getId());
        assertEquals("Sample Book", bookDTO.getName());
    }
}

