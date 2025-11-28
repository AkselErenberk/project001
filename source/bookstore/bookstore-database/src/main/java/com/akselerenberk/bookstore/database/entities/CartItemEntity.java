package com.akselerenberk.bookstore.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "app_cart_item")
@Table(name = "CART_ITEM")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@ToString(exclude = {"cart", "book"})
public class CartItemEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq")
    @SequenceGenerator(
            name = "cart_item_seq",
            sequenceName = "CART_ITEM_SEQ",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CART_ID", nullable = false)
    private CartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private BookEntity book;

    @Positive
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "LAST_UPDATED_TIME", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @Column(name = "CREATION_TIME", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationTime;

    public BigDecimal getTotalPrice() {
        if (book == null || book.getPrice() == null || quantity <= 0) {
            return BigDecimal.ZERO;
        }
        return book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}