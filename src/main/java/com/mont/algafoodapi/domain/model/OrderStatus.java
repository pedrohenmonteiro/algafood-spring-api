package com.mont.algafoodapi.domain.model;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELED("Canceled", CREATED);

    private String description;
    private List<OrderStatus> prevStatus;

    OrderStatus(String description, OrderStatus... prevStatus) {
        this.description = description;
        this.prevStatus = Arrays.asList(prevStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean cantChangeTo(OrderStatus newStatus) {
        return !newStatus.prevStatus.contains(this);
    }
}
