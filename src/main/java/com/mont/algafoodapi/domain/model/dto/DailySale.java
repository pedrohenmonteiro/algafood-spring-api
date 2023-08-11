package com.mont.algafoodapi.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DailySale {
    
    private LocalDate date;
    private Long totalSales;
    private BigDecimal totalBilled;
}
