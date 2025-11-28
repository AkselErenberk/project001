package com.akselerenberk.bookstore.database.repositories;

import com.akselerenberk.bookstore.database.entities.AccountEntity;
import com.akselerenberk.bookstore.database.entities.CartEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends ListCrudRepository<CartEntity, Integer> {
    Optional<CartEntity> findByAccount(final AccountEntity accountEntity);
}