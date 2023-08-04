package com.mont.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "tb_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal subtotal;

    private BigDecimal totalValue;

    private BigDecimal deliveryFee;
    
    @CreationTimestamp
    private OffsetDateTime creationDate;

    private OffsetDateTime cancelDate;

    private OffsetDateTime confirmDate;

    private OffsetDateTime deliveredDate;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();


    public void calcTotalValue() {
        getItems().forEach(OrderItem::calcTotalPrice);

        this.subtotal = getItems().stream().map(item -> item.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalValue = this.subtotal.add(this.deliveryFee);
    }
}
