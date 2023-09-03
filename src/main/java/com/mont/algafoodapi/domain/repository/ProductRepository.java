package com.mont.algafoodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mont.algafoodapi.domain.model.Product;
import com.mont.algafoodapi.domain.model.ProductPhoto;
import com.mont.algafoodapi.domain.model.Restaurant;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {
    
    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restauranteId, 
            @Param("product") Long productId);
    
    List<Product> findByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant")
    List<Product> findActiveByRestaurant(Restaurant restaurant);


    @Query("select p from ProductPhoto p join p.product x where x.restaurant.id = :restaurantId and p.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);
}
