package com.OrderService.Repository;

import com.OrderService.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Integer> {
}
