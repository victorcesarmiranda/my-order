package com.example.myorder.repositories;

import com.example.myorder.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReposity extends JpaRepository<Order, Integer> {
}
