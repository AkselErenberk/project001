package com.akselerenberk.bookstore.adapters.mappers;

import com.akselerenberk.bookstore.core.models.Book;
import com.akselerenberk.bookstore.database.entities.BookEntity;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class BookMapper {
    public Book model(BookEntity entity) {
        return Book.builder()
                .id(entity.getId().toString())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .price(entity.getPrice())
                .build();
    }

    public BookEntity entity(Book model) {
        return BookEntity.builder()
                .id(Objects.isNull(model.id()) ? null : Long.valueOf(model.id()))
                .title(model.title())
                .author(model.author())
                .price(model.price())
                .build();
    }
}
