package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.mont.algafoodapi.api.mapper.OrderMapper;
import com.mont.algafoodapi.api.mapper.OrderSummaryMapper;
import com.mont.algafoodapi.api.model.OrderDto;
import com.mont.algafoodapi.api.model.OrderSummaryDto;
import com.mont.algafoodapi.api.model.input.OrderInputDto;
import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Order;
import com.mont.algafoodapi.domain.repository.OrderRepository;
import com.mont.algafoodapi.domain.repository.UserRepository;

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
    
    public List<OrderSummaryDto> findAll() {
        return orderSummaryMapper.toCollectionDto(orderRepository.findAll());
    }

    public OrderDto findById(Long id) {
        var order = getOrder(id);
        return orderMapper.fromEntityToDto(order);
    }



    @Transactional
    public OrderDto create(OrderInputDto orderInputDto) {
        var order = orderMapper.fromDtoToEntity(orderInputDto);
        var user = userRepository.findById(1L).orElseThrow();
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


    protected Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource order id " + id + " not found"));
    }
}
