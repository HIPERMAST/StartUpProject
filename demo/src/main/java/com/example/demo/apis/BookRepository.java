package com.example.demo.apis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

    BookEntity findByNameAndDescription(String name, String description);
}
