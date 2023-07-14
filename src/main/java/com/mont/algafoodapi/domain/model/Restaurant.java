package com.mont.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mont.algafoodapi.Groups;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // @NotNull
    // @NotEmpty
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @PositiveOrZero
    @NotNull
    private BigDecimal deliveryFee;

    // @JsonIgnore
    @JsonIgnoreProperties(value = "name", allowGetters = true)
    @Valid
    @NotNull
    @ManyToOne  // (fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id", nullable = false)
    @ConvertGroup(from = Default.class, to = Groups.CuisineId.class)
    private Cuisine cuisine;

    // @JsonIgnore
    @Embedded
    private Address address;


    // @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dateCreation;

    // @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime dateLastUpdate;


    // @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
        joinColumns = @JoinColumn(name = "restaurant_id"),
        inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();


    // @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();    
}
