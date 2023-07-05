package com.mont.algafoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Address {
    
    @Column(name="address_zipcode")
    private String zipcode;
    @Column(name="address_street")
    private String street;
    @Column(name="address_number")
    private String number;
    @Column(name="address_complement")
    private String complement;
    @Column(name="address_neighbourhood")
    private String neighbourhood;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;
}
