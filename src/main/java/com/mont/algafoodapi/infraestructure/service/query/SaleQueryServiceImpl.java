package com.mont.algafoodapi.infraestructure.service.query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mont.algafoodapi.domain.filter.DailySaleFilter;
import com.mont.algafoodapi.domain.model.Order;
import com.mont.algafoodapi.domain.model.OrderStatus;
import com.mont.algafoodapi.domain.model.dto.DailySale;
import com.mont.algafoodapi.domain.service.SaleQueryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DailySale> findByDailySales(DailySaleFilter filter, String timeOffset) {
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(Order.class);

        List<Predicate> predicates = new ArrayList<>();

        var functionConvertTzCreationDate = builder.function(
            "convert_tz",
            LocalDate.class,
            root.get("creationDate"),
            builder.literal("+00:00"),
            builder.literal(timeOffset)
        );

        var functionCreationDate = builder.function("date", LocalDate.class, functionConvertTzCreationDate);

        var selection =  builder.construct(DailySale.class,
            functionCreationDate,
            builder.count(root.get("id")),
            builder.sum(root.get("totalValue"))
        );

        if(filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant").get("id"), filter.getRestaurantId()));
        }

        if(filter.getCreationDateInit() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"), filter.getCreationDateInit()));
        }

        if(filter.getCreationDateEnd() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"), filter.getCreationDateEnd()));
        }

        predicates.add(root.get("status").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERED));
        

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionCreationDate);

        return em.createQuery(query).getResultList();
    }
    
}


// select date(p.creation_date) as creation_date,
//        count(p.id) as total_sales,
//        sum(p.total_value) as total_billed
       
//        from order order o

//        group by date(o.creation_date)
 