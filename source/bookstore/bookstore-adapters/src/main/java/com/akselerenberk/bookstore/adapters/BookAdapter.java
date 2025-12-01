package com.akselerenberk.bookstore.adapters;

import com.akselerenberk.bookstore.adapters.mappers.BookMapper;
import com.akselerenberk.bookstore.common.exception.BadRequestException;
import com.akselerenberk.bookstore.core.models.Book;
import com.akselerenberk.bookstore.core.ports.BookPort;
import com.akselerenberk.bookstore.database.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class BookAdapter implements BookPort {

    private final BookRepository repository;

    @Override
    public List<Book> retrieveAllBooks() {
        return repository.findAll().stream().map(BookMapper::model).toList();
    }

    @Override
    public Book saveBook(final Book book) {
        if (Objects.nonNull(book.id()) && !repository.existsById(parseLongOrThrow(book.id()))) {
            throw new BadRequestException("Book with id " + book.id() + " doesn't exists");
        }
        val bookEntity = repository.save(BookMapper.entity(book));
        return BookMapper.model(bookEntity);
    }

    @Override
    public void deleteBook(final String bookId) {
        repository.deleteById(parseLongOrThrow(bookId));
    }

    @Override
    public Book retrieveBook(String bookId) {
        return repository.findById(parseLongOrThrow(bookId)).map(BookMapper::model).orElseThrow(() -> new BadRequestException("No book with id equal to " + bookId));
    }

    private Long parseLongOrThrow(String bookId) {
        try {
            return Long.valueOf(bookId);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid bookId format: " + bookId);
        }
    }

}
