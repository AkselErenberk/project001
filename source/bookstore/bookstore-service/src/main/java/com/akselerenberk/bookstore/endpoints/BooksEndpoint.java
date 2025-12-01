package com.akselerenberk.bookstore.endpoints;

import com.akselerenberk.bookstore.api.BooksApi;
import com.akselerenberk.bookstore.core.models.Book;
import com.akselerenberk.bookstore.core.ports.BookPort;
import com.akselerenberk.bookstore.dto.BookDTO;
import com.akselerenberk.bookstore.dto.UpdateBookRequestDTO;
import com.akselerenberk.bookstore.mappers.BookMapper;
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
    public ResponseEntity<BookDTO> addBook(UpdateBookRequestDTO updateBookRequestDTO) {
        val book = bookPort.saveBook(Book.builder().author(updateBookRequestDTO.getAuthor()).price(updateBookRequestDTO.getPrice()).title(updateBookRequestDTO.getTitle()).build());
        return ResponseEntity.ok().body(BookMapper.dto(book));
    }

    @Override
    public ResponseEntity<Void> deleteBook(String id) {
        bookPort.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BookDTO>> retrieveAllBooks() {
        val books = bookPort.retrieveAllBooks();
        val bookDTOs = books.stream().map(BookMapper::dto).toList();
        return ResponseEntity.ok().body(bookDTOs);
    }

    @Override
    public ResponseEntity<BookDTO> retrieveBook(String id) {
        val book = bookPort.retrieveBook(id);
        return ResponseEntity.ok(BookMapper.dto(book));
    }

    @Override
    public ResponseEntity<BookDTO> updateBook(String id, UpdateBookRequestDTO updateBookRequestDTO) {
        bookPort.saveBook(Book.builder().id(id).author(updateBookRequestDTO.getAuthor()).price(updateBookRequestDTO.getPrice()).title(updateBookRequestDTO.getTitle()).build());
        return ResponseEntity.ok().build();
    }
}
