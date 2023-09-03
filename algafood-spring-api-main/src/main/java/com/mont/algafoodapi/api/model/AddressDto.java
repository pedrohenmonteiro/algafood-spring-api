package com.mont.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
        
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String neighbourhood;
    private CitySummaryDto city;
}
