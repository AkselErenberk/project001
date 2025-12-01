package com.akselerenberk.bookstore.mappers;

import com.akselerenberk.bookstore.core.models.Book;
import com.akselerenberk.bookstore.dto.BookDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookMapper {
    public Book model(BookDTO dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .price(dto.getPrice())
                .build();
    }

    public BookDTO dto(Book model) {
        return BookDTO.builder()
                .id(model.id())
                .title(model.title())
                .author(model.author())
                .price(model.price())
                .build();
    }
}
