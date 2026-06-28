package com.OrderService.SpringAOPProxy;

import com.OrderService.FeignClient.PaymentFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.example.DTO.Payment.PaymentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    int attempt =1;
    private final PaymentFeign paymentFeign;

    public PaymentService(PaymentFeign paymentFeign) {
        this.paymentFeign = paymentFeign;
    }

    @Retry(name = "paymentServiceCB", fallbackMethod = "paymentFallback")
   @CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "paymentFallback")
    public PaymentModel paymentStatus(Integer orderNumber) {
        log.info("RETRY PAYMENT method called {} times at {}", attempt++, new Date());

        return paymentFeign.paymentstatus(String.valueOf(orderNumber));
    }

    public PaymentModel paymentFallback(Integer orderNumber, Throwable e) {

        log.warn("PAYMENT FALLBACK TRIGGERED, orderNumber={}, error={}", orderNumber, e != null ? e.getMessage() : "unknown");

        PaymentModel fallback = new PaymentModel();
        fallback.setPaymentStatus("PAYMENT_FALLBACK");
        fallback.setNotificationStatus("PAYMENT_FALLBACK");

        return fallback;
    }
}