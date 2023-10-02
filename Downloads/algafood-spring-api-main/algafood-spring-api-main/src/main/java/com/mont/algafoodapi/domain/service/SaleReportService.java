package com.mont.algafoodapi.domain.service;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;

public interface SaleReportService {
    
    byte[] emitDailySales(DailySaleFilter filter, String timeOffset);

}
