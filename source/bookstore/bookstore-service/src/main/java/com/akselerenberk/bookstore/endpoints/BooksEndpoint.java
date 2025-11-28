package com.akselerenberk.bookstore.endpoints;

import com.akselerenberk.bookstore.api.BooksApi;
import com.akselerenberk.bookstore.core.models.Book;
import com.akselerenberk.bookstore.core.ports.BookPort;
import com.akselerenberk.bookstore.dto.BookDTO;
import com.akselerenberk.bookstore.dto.BookRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksEndpoint implements BooksApi {

    private final BookPort bookPort;

    @Override
    public ResponseEntity<Void> addBook(BookRequestDTO bookRequestDTO) {
        bookPort.saveBook(Book.builder().author(bookRequestDTO.getAuthor()).price(bookRequestDTO.getPrice()).title(bookRequestDTO.getTitle()).build());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteBook(String id) {
        bookPort.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BookDTO>> retrieveAllBooks() {
        val books = bookPort.retrieveAllBooks();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<BookDTO> retrieveBook(String id, BookDTO bookDTO) {
        val book = bookPort.retrieveBook(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateBook(String id, BookRequestDTO bookRequestDTO) {
        bookPort.saveBook(Book.builder().id(id).author(bookRequestDTO.getAuthor()).price(bookRequestDTO.getPrice()).title(bookRequestDTO.getTitle()).build());
        return ResponseEntity.ok().build();
    }
}
