package com.mont.algafoodapi.infraestructure.service.report;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.service.SaleQueryService;
import com.mont.algafoodapi.domain.service.SaleReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfSaleReportService implements SaleReportService{

    @Autowired
    private SaleQueryService saleQueryService;

    @Override
    public byte[] emitDailySales(DailySaleFilter filter, String timeOffset) {


        try {
        var inputStream = this.getClass().getResourceAsStream("/reports/daily-sales.jasper");

        var parameters = new HashMap<String, Object>();
        parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));


        var dailySales = saleQueryService.findByDailySales(filter, timeOffset);
        var dataSource = new JRBeanCollectionDataSource(dailySales);

        var jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new ReportException("It was not possible to emit daily sales report", e);
        }
    }
    
}
