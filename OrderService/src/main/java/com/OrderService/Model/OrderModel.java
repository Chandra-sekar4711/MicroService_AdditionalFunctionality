package com.OrderService.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Entity
public class OrderModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Reference to Customer Service (NO FK – microservice rule)
    @Column(nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private BigDecimal totalAmount;


    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String notification_status;



}
