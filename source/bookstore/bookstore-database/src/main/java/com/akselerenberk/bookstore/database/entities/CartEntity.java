package com.akselerenberk.bookstore.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CART")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class CartEntity {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(
            name = "cart_seq",
            sequenceName = "CART_SEQ",
            allocationSize = 1
    )
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false, unique = true)
    private AccountEntity account;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<CartItemEntity> items = new ArrayList<>();

    @Column(name = "LAST_UPDATED_TIME", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @Column(name = "CREATION_TIME", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationTime;

    public BigDecimal getTotalPrice() {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return items.stream()
                .map(CartItemEntity::getTotalPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(CartItemEntity item) {
        if (item == null) return;
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItemEntity item) {
        if (item == null) return;
        items.remove(item);
        item.setCart(null);
    }

}
