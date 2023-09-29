package com.mont.algafoodapi.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.domain.repository.OrderRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;


@Component
public class AppSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        String userId = jwt.getClaimAsString("user_id");

        if(userId == null) {
            return null;
        }

        return Long.valueOf(userId);
    }

    public boolean manageRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return false;
        }
        return restaurantRepository.existsResponsible(restaurantId, getUserId());
    }

    public boolean manageRestaurantOfOrder(String code) {
        return orderRepository.isOrderManagedBy(code, getUserId());
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean userAuthenticatedEqual(Long userId) {
		return getUserId() != null && userId != null
				&& getUserId().equals(userId);
	}

    public boolean hasScopeRead() {
        return hasAuthority("SCOPE_read");
    }

    public boolean hasScopeWrite() {
        return hasAuthority("SCOPE_write");
    }

    public boolean allowsSearchOrder(Long clientId, Long restaurantId) {
        return hasScopeRead() && (hasAuthority("QUERY_ORDERS") || userAuthenticatedEqual(clientId) || manageRestaurant(restaurantId));
    
    }

    public boolean allowsManageOrder(String orderCode) {
        return hasScopeRead() && (hasAuthority("MANAGE_ORDERS") || manageRestaurantOfOrder(orderCode));
    
    }
}
