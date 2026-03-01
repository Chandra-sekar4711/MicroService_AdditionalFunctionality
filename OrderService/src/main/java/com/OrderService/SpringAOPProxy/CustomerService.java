package com.OrderService.SpringAOPProxy;

import com.OrderService.FeignClient.CustomerFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.DTO.CustomerModel.CustomerModel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {
       int attempt =1;
    private final CustomerFeign customerFeign;

    public CustomerService(CustomerFeign customerFeign) {
        this.customerFeign = customerFeign;
    }

    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "customerFallback")
    public CustomerModel getCustomerById(Integer customerId) {
        return customerFeign.getcustomerByid(customerId);
    }

    public CustomerModel customerFallback(Integer customerId, Exception e) {

        System.out.println("🔥 CUSTOMER FALLBACK TRIGGERED");

        CustomerModel fallback = new CustomerModel();
        fallback.setId(0);
        fallback.setName("CUSTOMER_FALLBACK");
        fallback.setEmail("CUSTOMER_FALLBACK");
        fallback.setPhone("CUSTOMER_FALLBACK");
        fallback.setActive(false);

        return fallback;
    }

    @Retry(name = "customerServiceRT",fallbackMethod = "customerFallback")
    public CustomerModel customerretry_chceck(Integer customerId) {
        System.out.println("***************   RETRY CUSTOMER method called "+attempt++ +" times "+" at "+new Date());
        return customerFeign.simulateretry(customerId);
    }

}