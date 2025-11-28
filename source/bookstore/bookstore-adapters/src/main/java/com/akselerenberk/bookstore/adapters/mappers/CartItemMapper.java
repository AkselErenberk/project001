package com.akselerenberk.bookstore.adapters.mappers;

import com.akselerenberk.bookstore.core.models.CartItem;
import com.akselerenberk.bookstore.database.entities.CartEntity;
import com.akselerenberk.bookstore.database.entities.CartItemEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartItemMapper {
    public CartItem model(CartItemEntity entity) {
        return CartItem.builder()
                .book(BookMapper.model(entity.getBook()))
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .build();
    }

    public CartItemEntity entity(final CartItem model) {
        return CartItemEntity.builder()
                .book(BookMapper.entity(model.book()))
                .quantity(model.quantity())
                .build();
    }
}
