package com.akselerenberk.bookstore.mappers;

import com.akselerenberk.bookstore.core.models.Cart;
import com.akselerenberk.bookstore.dto.CartDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartMapper {
    public CartDTO dto(final Cart model) {
        return CartDTO.builder()
                .items(model.items().stream().map(CartItemMapper::dto).toList())
                .build();
    }
}
