package com.OrderService.FeignClient;

import org.example.DTO.CustomerModel.CustomerModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CUSTOMERSERVICE")
public interface CustomerFeign {

    @GetMapping("/customer/getcustomer/{id}")
    public CustomerModel getcustomerByid(@PathVariable Integer id);

    @GetMapping("/simulateretry/{id}")
    CustomerModel simulateretry(Integer customerId);
}
