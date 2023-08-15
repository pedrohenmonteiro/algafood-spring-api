package com.mont.algafoodapi.infraestructure.service.report;

import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.service.SaleReportService;

@Service
public class PdfSaleReportService implements SaleReportService{

    @Override
    public byte[] emitDailySales(DailySaleFilter filter, String timeOffset) {
        return null;
    }
    
}
