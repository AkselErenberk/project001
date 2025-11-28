package com.akselerenberk.bookstore.database.repositories;


import com.akselerenberk.bookstore.database.entities.BookEntity;
import com.akselerenberk.bookstore.database.entities.CartEntity;
import com.akselerenberk.bookstore.database.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    Optional<CartItemEntity> findByBookAndCart(BookEntity bookEntity, CartEntity cartEntity);
}
