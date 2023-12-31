package com.mont.algafoodapi.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.OrderMapper;
import com.mont.algafoodapi.api.mapper.OrderSummaryMapper;
import com.mont.algafoodapi.api.model.OrderDto;
import com.mont.algafoodapi.api.model.OrderSummaryDto;
import com.mont.algafoodapi.api.model.input.OrderInputDto;
import com.mont.algafoodapi.core.data.PageableTranslator;
import com.mont.algafoodapi.core.security.AppSecurity;
import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.filter.OrderFilter;
import com.mont.algafoodapi.domain.model.Order;
import com.mont.algafoodapi.domain.repository.OrderRepository;
import com.mont.algafoodapi.domain.repository.UserRepository;
import com.mont.algafoodapi.infraestructure.repository.spec.OrderSpecs;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSummaryMapper orderSummaryMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private ProductService productService;

    @Autowired 
    private AppSecurity appSecurity;
    
    public Page<OrderSummaryDto> findAll(OrderFilter filter, Pageable pageable) {
        pageable = translatePageable(pageable);
        var ordersPage = orderRepository.findAll(OrderSpecs.usingFilter(filter), pageable);
        var ordersDto = orderSummaryMapper.toCollectionDto(ordersPage.getContent());
        return new PageImpl<>(ordersDto, pageable, ordersPage.getTotalElements());
    }

    public OrderDto findByCode(String code) {
        var order = getOrder(code);
        return orderMapper.fromEntityToDto(order);
    }



    @Transactional
    public OrderDto create(OrderInputDto orderInputDto) {
        var order = orderMapper.fromDtoToEntity(orderInputDto);
        var user = userRepository.findById(appSecurity.getUserId()).orElseThrow();
        order.setClient(user);

        validateOrder(order);
        validateItems(order);

        order.setDeliveryFee(order.getRestaurant().getDeliveryFee());
        order.calcTotalValue();

        return orderMapper.fromEntityToDto(orderRepository.save(order));

}

private void validateOrder(Order order) {
    var city = cityService.getCity(order.getAddress().getCity().getId());
    var client = userService.getUser(order.getClient().getId());
    var restaurant = restaurantService.getRestaurant(order.getRestaurant().getId());
    var paymentMethod = paymentMethodService.getPaymentMethod(order.getPaymentMethod().getId());

    order.getAddress().setCity(city);
    order.setClient(client);
    order.setRestaurant(restaurant);
    order.setPaymentMethod(paymentMethod);
    
    if (restaurant.dontAcceptPaymentMethod(paymentMethod)) {
        throw new BadRequestException("Payment method " + paymentMethod.getDescription() + " is not accepted in this restaurant");
    }
}

private void validateItems(Order order) {
    order.getItems().forEach(item -> {
        var product = productService.getProduct(order.getRestaurant().getId(), item.getProduct().getId());
        
        item.setOrder(order);
        item.setProduct(product);
        item.setUnitPrice(product.getPrice());
    });
}


    protected Order getOrder(String code) {
        return orderRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Resource order code " + code + " not found"));
    }

    
    private Pageable translatePageable(Pageable apiPageable) {
        var mapping = Map.of(
            "code", "code",
            "client.name", "client.name",
            "restaurant.name", "restaurant.name",
            "totalValue", "totalValue"
        );

        return PageableTranslator.translate(apiPageable, mapping);
    }
}
