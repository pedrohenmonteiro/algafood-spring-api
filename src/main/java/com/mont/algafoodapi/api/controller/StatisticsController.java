package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.model.dto.DailySale;
import com.mont.algafoodapi.domain.service.SaleQueryService;
import com.mont.algafoodapi.domain.service.SaleReportService;

@RestController
@RequestMapping(path = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsController {

    @Autowired
    private SaleQueryService saleQueryService;

    @Autowired
    private SaleReportService saleReportService;
    
    @CheckSecurity.Statistics.allowsQuery
    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DailySale>> findDailySales(DailySaleFilter filter, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return ResponseEntity.ok(saleQueryService.findByDailySales(filter,timeOffset));
    }

    @CheckSecurity.Statistics.allowsQuery
    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> findDailySalesPdf(DailySaleFilter filter, @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        byte[] bytesPdf = saleReportService.emitDailySales(filter, timeOffset);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .headers(headers)
                    .body(bytesPdf);
    }
}
