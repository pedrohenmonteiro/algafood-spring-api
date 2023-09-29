package com.mont.algafoodapi.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.domain.repository.RestaurantRepository;


@Component
public class AppSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;
    
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
        return restaurantRepository.existsResponsible(restaurantId, getUserId());
    }

    public boolean userAuthenticatedEqual(Long userId) {
		return getUserId() != null && userId != null
				&& getUserId().equals(userId);
	}
}
