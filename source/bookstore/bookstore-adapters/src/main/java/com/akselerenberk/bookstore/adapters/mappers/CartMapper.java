package com.akselerenberk.bookstore.adapters.mappers;

import com.akselerenberk.bookstore.core.models.Cart;
import com.akselerenberk.bookstore.database.entities.CartEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartMapper {
    public Cart model(CartEntity entity) {
        return Cart.builder()
                .totalPrice(entity.getTotalPrice())
                .items(entity.getItems().stream().map(CartItemMapper::model).toList())
                .build();
    }

    public CartEntity entity(final Cart model) {
        return CartEntity.builder()
                .items(model.items().stream().map(CartItemMapper::entity).toList())
                .build();
    }
}
