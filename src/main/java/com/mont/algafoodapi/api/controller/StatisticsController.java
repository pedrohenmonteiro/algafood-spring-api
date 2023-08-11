package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.model.dto.DailySale;
import com.mont.algafoodapi.domain.service.SaleQueryService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private SaleQueryService saleQueryService;
    
    @GetMapping("/daily-sales")
    public List<DailySale> finDailySales(DailySaleFilter filter) {
        return saleQueryService.findByDailySales(filter);
    }
}
