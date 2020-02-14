package com.example.myorder.services;

import com.example.myorder.api.dtos.CreateProductDto;
import com.example.myorder.api.dtos.ProductResponseDto;
import com.example.myorder.api.dtos.RestaurantResponseDto;
import com.example.myorder.entities.Product;
import com.example.myorder.entities.Restaurant;
import com.example.myorder.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto create(CreateProductDto createProductDto) {
        Product product = productRepository.save(createProduct(createProductDto));

        return new ProductResponseDto()
                .setName(product.getName())
                .setRestaurant(createRestaurantResponseDto(product))
                .setValue(product.getValue());
    }

    private Product createProduct(CreateProductDto createProductDto){
        return new Product()
                .setName(createProductDto.getName())
                .setValue(createProductDto.getValue())
                .setRestaurant(restaurantService.findById(createProductDto.getRestaurantId()));
    }

    private RestaurantResponseDto createRestaurantResponseDto(Product product) {
        Restaurant restaurant = product.getRestaurant();
        return new RestaurantResponseDto()
                .setId(restaurant.getId())
                .setNome(restaurant.getName())
                .setPhone(restaurant.getPhone())
                .setEmail(restaurant.getEmail());

    }
}
