package com.akselerenberk.bookstore.core.ports;

import com.akselerenberk.bookstore.core.models.Book;

import java.util.List;

public interface BookPort {
    List<Book> retrieveAllBooks();

    Book saveBook(final Book book);

    void deleteBook(final String bookId);

    Book retrieveBook(final String bookId);
}