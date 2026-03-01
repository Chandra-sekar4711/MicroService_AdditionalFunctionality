package com.OrderService.FeignClient;

import org.example.DTO.Payment.PaymentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PAYMENTSERVICE")
public interface PaymentFeign {

    @GetMapping("/payment/paymentstatus/{id}")
    public PaymentModel paymentstatus(@PathVariable String id);

}
