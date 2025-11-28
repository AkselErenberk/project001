package com.akselerenberk.bookstore.database.repositories;


import com.akselerenberk.bookstore.database.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
