package com.akselerenberk.bookstore.mappers;

import com.akselerenberk.bookstore.core.models.CartItem;
import com.akselerenberk.bookstore.dto.CartItemDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartItemMapper {
    public CartItemDTO dto(final CartItem model) {
        return CartItemDTO.builder()
                .book(BookMapper.dto(model.book()))
                .quantity(model.quantity())
                .build();
    }
}
