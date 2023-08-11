package com.mont.algafoodapi.infraestructure.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.model.Order;
import com.mont.algafoodapi.domain.model.dto.DailySale;
import com.mont.algafoodapi.domain.service.SaleQueryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DailySale> findByDailySales(DailySaleFilter filter) {
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);


        var functionDateCreation = builder.function("date", LocalDate.class, root.get("creationDate"));

        var selection =  builder.construct(DailySale.class,
            functionDateCreation,
            builder.count(root.get("id")),
            builder.sum(root.get("totalValue"))
        );

        query.select(selection);
        query.groupBy(functionDateCreation);

        return em.createQuery(query).getResultList();
    }
    
}


// select date(p.creation_date) as creation_date,
//        count(p.id) as total_sales,
//        sum(p.total_value) as total_billed
       
//        from order order o

//        group by date(o.creation_date)
 