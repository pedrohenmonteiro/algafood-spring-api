package com.mont.algafoodapi.domain.service;

import java.util.List;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.model.dto.DailySale;

public interface SaleQueryService {

    List<DailySale> findByDailySales(DailySaleFilter filter);
}
