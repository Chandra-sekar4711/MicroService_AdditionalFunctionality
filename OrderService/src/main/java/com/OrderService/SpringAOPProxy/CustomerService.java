package com.OrderService.SpringAOPProxy;

import com.OrderService.FeignClient.CustomerFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.DTO.CustomerModel.CustomerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
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

        log.warn("CUSTOMER FALLBACK TRIGGERED, customerId={}, error={}", customerId, e.getMessage());

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
        log.info("RETRY CUSTOMER method called {} times at {}", attempt++, new Date());
        return customerFeign.simulateretry(customerId);
    }

}